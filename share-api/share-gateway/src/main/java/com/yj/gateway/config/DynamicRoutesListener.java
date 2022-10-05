package com.yj.gateway.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import com.yj.gateway.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-28
 **/

@Component
@Slf4j
public class DynamicRoutesListener implements Listener {
    @Resource
    private GatewayService gatewayService;

    @Override
    public Executor getExecutor() {
        log.info("getExecutor");
        return null;
    }

    @Override
    public void receiveConfigInfo(String s) {
        log.info("receive routes change: {}", s);

        List<RouteDefinition> routeDefinitions = JSON.parseArray(s, RouteDefinition.class);
        gatewayService.updateRoutes(routeDefinitions);
    }
}

