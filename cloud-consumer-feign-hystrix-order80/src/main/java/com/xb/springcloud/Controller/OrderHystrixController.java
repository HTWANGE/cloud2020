package com.xb.springcloud.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xb.springcloud.service.PaymentFeignHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by XB on 2020/4/21.
 */

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentFeignHystrixService paymentFeignHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentFeignHystrixService.paymentInfo_OK(id);
        return result + "\t" + serverPort;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    //@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="1500")
    //})
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        //运行报错
        int age = 10/0;
        return age + "";

        //运行超时
        //String result = paymentFeignHystrixService.paymentInfo_TimeOut(id);
        //return result + "\t" + serverPort;
    }
    //下面是指定fallback
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒后重试或者自己运行出错请检查自己，o(╥﹏╥)o";
    }

    //下面是全局fallback
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后重试，/o(╥﹏╥)o/~~";
    }
}
