package com.xb.springcloud.controller;

import com.xb.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by XB on 2020/5/17.
 */

@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "sendMessage")
    public String sendMessage(){
        return iMessageProvider.send();
    }
}
