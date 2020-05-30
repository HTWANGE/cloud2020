package com.xb.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by XB on 2020/5/9.
 * 配置中心主启动类
 */
@SpringBootApplication
//配置中心，全局通知 curl -X POST "http://localhost:3344/actuator/bus-refresh"
//配置中心，定点通知 curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String [] args){
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
