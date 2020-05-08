package com.xb.springcloud.controller;

import com.xb.springcloud.entities.CommonResult;
import com.xb.springcloud.entities.Payment;
import com.xb.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by XB on 2020/4/16.
 */

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consumer/payment/feign/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        log.info("feign server port:" + serverPort);
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openfeign-ribbon，客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
