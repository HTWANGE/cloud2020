package com.xb.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * Created by XB on 2020/5/7.
 */

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        log.info("****************** come in MyLogGateWayFilter：" + new Date());
        String uname = serverWebExchange.getRequest().getQueryParams().getFirst("uname");
        if(uname == null){
            log.info("************ 用户名为null，非法用户，o(╥﹏╥)o");
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return serverWebExchange.getResponse().setComplete();
        }
        return gatewayFilterChain.filter(serverWebExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
