package com.example.wallet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.wallet.CommonConstants.WALLET_UPDATED_TOPIC_STATUS;
import static com.example.wallet.CommonConstants.WALLET_UPDATE_STATUS_FAILED;
import static com.example.wallet.CommonConstants.WALLET_UPDATE_STATUS_SUCCESS;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    @KafkaListener(topics = CommonConstants.USER_CREATED_TOPIC, groupId = "EWallet_Group")
    public void createWallet(String message) throws ParseException {
        logger.debug("In WalletService.createWallet method with message: {}",message);
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        Long userId = (Long) data.get(CommonConstants.USER_CREATED_TOPIC_USERID);
        String phoneNumber = (String) data.get(CommonConstants.USER_CREATED_TOPIC_PHONE_NUMBER);
        String identifierKey = (String) data.get(CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_KEY);
        String identifierValue = (String) data.get(CommonConstants.USER_CREATED_TOPIC_IDENTIFIER_VALUE);

        Wallet wallet = Wallet.builder()
                .userId(userId).phoneNumber(phoneNumber)
                .userIdentifier(UserIdentifier.valueOf(identifierKey))
                .identifierValue(identifierValue)
                .balance(10.0)
                .build();

        walletRepository.save(wallet);
        logger.debug("Out WalletService.createWallet method");

    }

    @KafkaListener(topics = CommonConstants.TRANSACTION_CREATED_TOPIC, groupId = "EWallet_Group")
    @Transactional
    public void updateWalletForTransaction(String message) throws ParseException, JsonProcessingException {
        logger.debug("In WalletService.updateWalletForTransaction method with message: {}",message);
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String senderId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER);
        String receiverId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT);
        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTIONId);

        // validate if sender and receiver wallet's are in active state
        Wallet senderWallet = walletRepository.findByPhoneNumber(senderId);
        Wallet receiverWallet = walletRepository.findByPhoneNumber(receiverId);

        //publish the event after validating and updating wallets of sender and receiver
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER,senderId);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER,receiverId);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT,amount);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTIONId,transactionId);

        if(senderWallet == null || receiverWallet == null || senderWallet.getBalance() < amount){
            jsonObject.put(WALLET_UPDATED_TOPIC_STATUS,WALLET_UPDATE_STATUS_FAILED);
        } else{

            //debit amount from sender's wallet
            walletRepository.updateWallet(senderId,0-amount);

            //credit amount to receiver's wallet
            walletRepository.updateWallet(receiverId,amount);
            jsonObject.put("walletUpdateStatus",WALLET_UPDATE_STATUS_SUCCESS);
        }

        kafkaTemplate.send(CommonConstants.WALLET_UPDATED_TOPIC,
                objectMapper.writeValueAsString(jsonObject));
        logger.debug("Out WalletService.updateWalletForTransaction method");
    }
}
