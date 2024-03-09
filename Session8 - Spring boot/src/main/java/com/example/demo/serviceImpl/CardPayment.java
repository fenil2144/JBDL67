package com.example.demo.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.service.PaymentInterf;

@Service("CardPayment")
public class CardPayment implements PaymentInterf {

    private static Logger logger = LoggerFactory.getLogger(CardPayment.class);

    @Override
    public void pay() {
        logger.info("In CardPayment: {}",this);
    }
}
