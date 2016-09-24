package com.zheng.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * 实现springboot与redis 结合
 * 需要实现cachemanager, rediscache具体操作模板类
 * Created by zhenglian on 2016/9/24.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        CacheManager manager = new RedisCacheManager(redisTemplate);
        return manager;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        //如果采用redis默认的序列方式，那么下面的代码不能写，否则如果参数中存在非String类型参数则会报错
        //但是默认key生成策略的一个缺点就是通过redis客户端查看key得到的是乱码

        //采用自定义key生成策略的时候(也就是下面重写了KeyGenerator生成策略)，需要加上下面自定义key实现并注册到redis模板中
        RedisSerializer<String> serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        return template;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... args) {
                StringBuilder builder = new StringBuilder();
                builder.append(o.getClass().getName())
                        .append(method.getName());
                for(Object arg : args) {
                    builder.append(arg.toString());
                }
                System.out.println("keyGenerator: " + builder.toString());
                return builder.toString();
            }
        };
    }
}
