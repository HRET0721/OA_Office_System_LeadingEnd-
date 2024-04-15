package com.breath.config;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/8 12:34
 */


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.breath.listeners", "org.hret.entity.utils"})
public class ListenerMqConfig {
}
