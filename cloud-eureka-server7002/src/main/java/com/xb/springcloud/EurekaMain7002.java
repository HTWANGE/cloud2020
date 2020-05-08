package com.xb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by XB on 2020/4/11.
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String [] args){
        SpringApplication.run(EurekaMain7002.class, args);
    }
}
