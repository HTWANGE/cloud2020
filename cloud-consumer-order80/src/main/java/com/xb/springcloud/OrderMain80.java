package com.xb.springcloud;

import com.xb.springcloud.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by XB on 2020/4/9.
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "cloud-provider-service", configuration = MySelfRule.class) //将Ribbon的IRule规则改为随机
public class OrderMain80 {
    public static void main(String [] args){
        SpringApplication.run(OrderMain80.class, args);
    }
}
