package com.xb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by XB on 2020/4/14.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006 {
    public static void main(String [] args){
        SpringApplication.run(PaymentMain8006.class, args);
    }
}
