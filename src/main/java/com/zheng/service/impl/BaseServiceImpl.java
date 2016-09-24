package com.zheng.service.impl;

import com.google.common.collect.Lists;
import com.zheng.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenglian on 2016/9/24.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract CrudRepository<T, Serializable> getRepository();

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void test() {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello", "world");
        System.out.println(operations.get("hello"));
    }

    @Cacheable(value = "demoInfo")
    @Override
    public T get(Serializable id) {
        System.out.println("从数据库中获取" + id + "对应的用户......");
        return getRepository().findOne(id);
    }

    @CachePut(value = "demoInfo")
    @Override
    public void update(T t) {
        getRepository().save(t);
    }

    @CacheEvict(value = "demoInfo")
    @Override
    public void save(T t) {
        getRepository().save(t);
    }

    @CacheEvict(value = "demoInfo")
    @Override
    public void delete(Serializable id) {
        getRepository().delete(id);
    }

    @Override
    public List<T> findAll() {
        return Lists.newArrayList(getRepository().findAll());
    }

    @CacheEvict(value = "demoInfo", allEntries = true)
    @Override
    public void clearCache() {
        System.out.println("清空缓存");
    }
}
