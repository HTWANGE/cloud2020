package com.xb.springcloud.service.impl;

import com.xb.springcloud.dao.PaymentDao;
import com.xb.springcloud.entities.Payment;
import com.xb.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by XB on 2020/4/9.
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
