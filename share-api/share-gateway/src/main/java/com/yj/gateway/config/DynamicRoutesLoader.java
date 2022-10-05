package com.yj.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yj
 * @create: 2022-09-28
 **/

@Slf4j
@Configuration
public class DynamicRoutesLoader implements InitializingBean {
    @Resource
    private NacosConfigManager configManager;

    @Resource
    private NacosConfigProperties configProperties;

    @Resource
    private DynamicRoutesListener dynamicRoutesListener;

    private static final String ROUTES_CONFIG = "routes-config.json";

    @Override
    public void afterPropertiesSet() throws Exception {
        // 首次加载配置
        String config = configManager.getConfigService().getConfig(ROUTES_CONFIG, configProperties.getGroup(), 10000);
        dynamicRoutesListener.receiveConfigInfo(config);

        // 注册监听器
        configManager.getConfigService().addListener(ROUTES_CONFIG,configProperties.getGroup(),dynamicRoutesListener);
    }
}
