package com.xb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by XB on 2020/4/22.
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String [] args){
        //启动后，可视化界面访问地址：http://localhost:9001/hystrix
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
