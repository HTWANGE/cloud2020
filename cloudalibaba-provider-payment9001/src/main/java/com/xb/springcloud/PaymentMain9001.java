package com.xb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by XB on 2020/5/19.
 */

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9001 {
    public static void main(String [] args){
        SpringApplication.run(PaymentMain9001.class, args);
    }
}
