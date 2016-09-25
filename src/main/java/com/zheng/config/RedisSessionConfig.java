package com.zheng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 添加分布式session状态管理
 * Created by zhenglian on 2016/9/25.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60) //1分钟失效
public class RedisSessionConfig {

}
