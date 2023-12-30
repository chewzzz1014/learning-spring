package com.example.sqch10ex5.service;

import com.example.sqch10ex5.exception.NotEnoughMoneyException;
import com.example.sqch10ex5.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
