package com.xb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by XB on 2020/4/16.
 */

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String [] args){
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
