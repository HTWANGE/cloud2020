package com.xb.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by XB on 2020/5/13.
 */

@RestController
@RefreshScope //配置文件自动刷新 【执行这条命令：curl -X POST "http://localhost:3355/actuator/refresh"，3355获取的yml信息才会更新】
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
