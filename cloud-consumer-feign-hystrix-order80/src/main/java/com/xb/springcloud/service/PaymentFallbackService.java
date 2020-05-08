package com.xb.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * Created by XB on 2020/4/21.
 */

@Component
public class PaymentFallbackService implements PaymentFeignHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-- PaymentFallbackService fallback paymentInfo_OK ，o(T o T)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-- PaymentFallbackService fallback paymentInfo_TimeOut ，o(T o T)o";
    }
}
