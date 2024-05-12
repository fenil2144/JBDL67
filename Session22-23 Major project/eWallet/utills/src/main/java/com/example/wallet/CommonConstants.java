package com.example.wallet;

public class CommonConstants {

    // user related constants
    public static final String USER_CREATED_TOPIC_USERID = "userId";
    public static final String USER_CREATED_TOPIC_PHONE_NUMBER = "phoneNumber";
    public static final String USER_CREATED_TOPIC_IDENTIFIER_KEY= "userIdentifier";
    public static final String USER_CREATED_TOPIC_IDENTIFIER_VALUE = "identifierValue";
    public static final String USER_CREATED_TOPIC_EMAIL = "emailId";

    // topic used for publishing event when new user is created
    public static final String USER_CREATED_TOPIC = "user_created";


    // transaction related constants
    public static final String TRANSACTION_CREATED_TOPIC_SENDER = "sender";
    public static final String TRANSACTION_CREATED_TOPIC_RECEIVER = "receiver";
    public static final String TRANSACTION_CREATED_TOPIC_AMOUNT = "amount";
    public static final String TRANSACTION_CREATED_TOPIC_TRANSACTIONId = "transactionId";

    // topic used for publishing event when new transaction is created
    public static final String TRANSACTION_CREATED_TOPIC = "transaction_created";
    public static final String TRANSACTION_COMPLETED_TOPIC = "transaction_created";


    //wallet related constants
    public static final String WALLET_UPDATE_STATUS_SUCCESS = "success";
    public static final String WALLET_UPDATE_STATUS_FAILED = "failed";

    // topic used for publishing event when wallet is updated for any transaction
    public static final String WALLET_UPDATED_TOPIC = "wallet_updated";

    public static final String WALLET_UPDATED_TOPIC_STATUS = "walletUpdateStatus";




}
