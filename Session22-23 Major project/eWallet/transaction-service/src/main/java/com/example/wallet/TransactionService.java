package com.example.wallet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public String transact(String sender, String receiver, double amount, String reason) throws JsonProcessingException {
        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderId(sender).receiverId(receiver)
                .amount(amount).reason(reason)
                .transactionStatusEnum(TransactionStatusEnum.PENDING)
                .build();

        transactionRepository.save(transaction);

        //publish the event after initializing the transaction which will be listened by consumers
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER,sender);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER,receiver);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT,amount);
        jsonObject.put(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTIONId,transaction.getTransactionId());

        kafkaTemplate.send(CommonConstants.TRANSACTION_CREATED_TOPIC,
                objectMapper.writeValueAsString(jsonObject));

        return transaction.getTransactionId();
    }

    @KafkaListener(topics = CommonConstants.WALLET_UPDATED_TOPIC, groupId = "EWallet_Group")
    public void updateTransaction(String message) throws ParseException, JsonProcessingException {
        logger.debug("In WalletService.updateTransaction method with message: {}",message);
        JSONObject data = (JSONObject) new JSONParser().parse(message);

        String senderId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_SENDER);
        String receiverId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_RECEIVER);
        Double amount = (Double) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_AMOUNT);
        String transactionId = (String) data.get(CommonConstants.TRANSACTION_CREATED_TOPIC_TRANSACTIONId);
        String walletUpdateStatus = (String) data.get(CommonConstants.WALLET_UPDATED_TOPIC_STATUS);

        TransactionStatusEnum transactionStatusEnum;
        String receiverMail = null;
        JSONObject senderObj = getUserFromUserService(senderId);
        String senderEmail = (String) senderObj.get("email");

        if(walletUpdateStatus.equalsIgnoreCase("success")){
            JSONObject receiverObj = getUserFromUserService(receiverId);
            receiverMail = (String) receiverObj.get("email");
            transactionStatusEnum = TransactionStatusEnum.SUCCESS;
        } else{
            transactionStatusEnum = TransactionStatusEnum.FAILED;
        }
        transactionRepository.updateTransaction(transactionId, transactionStatusEnum);

        String senderMessage = "Hi, your transaction with id "+transactionId+ " got "+ walletUpdateStatus;

        JSONObject senderEmailObj = new JSONObject();
        senderEmailObj.put("email",senderEmail);
        senderEmailObj.put("message",senderMessage);
        kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC,
                objectMapper.writeValueAsString(senderEmailObj));

        if(walletUpdateStatus.equalsIgnoreCase("success")){
            String receiverMessage = "Hi, You have received Rs. "+ amount + " from "
                    +senderId + " in your wallet linked with phone number "+ receiverId;

            JSONObject receiverEmailObj = new JSONObject();
            receiverEmailObj.put("email",receiverMail);
            receiverEmailObj.put("message",receiverMessage);
            kafkaTemplate.send(CommonConstants.TRANSACTION_COMPLETED_TOPIC,
                    objectMapper.writeValueAsString(receiverEmailObj));
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JSONObject requestedUser = getUserFromUserService(username);

        List<GrantedAuthority> authorities = null;
        List<LinkedHashMap<String, String>> requestedAuthorities =
                (List<LinkedHashMap<String, String>>) requestedUser.get("authorities");

        authorities = requestedAuthorities.stream().map(x-> x.get("authority"))
                        .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

        return new User((String) requestedUser.get("username"),
                (String) requestedUser.get("password"),authorities);
    }

    private JSONObject getUserFromUserService(String username){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("service","serv123");
        HttpEntity httpEntityRequest = new HttpEntity(httpHeaders);

        return restTemplate.exchange("http://localhost:6001/admin/all/"+username,
                HttpMethod.GET,httpEntityRequest,JSONObject.class).getBody();
    }
}
