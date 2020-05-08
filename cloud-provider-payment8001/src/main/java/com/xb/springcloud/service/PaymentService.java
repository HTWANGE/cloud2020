package com.xb.springcloud.service;

import com.xb.springcloud.entities.Payment;

/**
 * Created by XB on 2020/4/9.
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
