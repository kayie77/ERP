package com.xhwl.erp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis Session 配置
 * @author jiayiwu
 * @date 2017年12月25日
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*1)
public class SessionConfig {
    // maxInactiveIntervalInSeconds: 设置Session失效时间，使用Redis Session之后
    //原Boot的server.session.timeout属性不再生效
}
