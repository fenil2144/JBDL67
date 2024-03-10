package com.example.demo.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.service.PaymentInterf;

@Service
@Primary
public class UPIPayment implements PaymentInterf{

    private static Logger logger = LoggerFactory.getLogger(UPIPayment.class);


    public void pay() {
        logger.info("In UPIPayment");
    }
}
