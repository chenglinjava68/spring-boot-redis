package com.zheng.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhenglian on 2016/9/24.
 */
public interface BaseService<T> {
    public T get(Serializable id);
    public void update(T t);
    public void save(T t);
    public void delete(Serializable id);
    public List<T> findAll();

    /**
     * 主要是用来测试redis是否生效
     */
    public void test();
    public void clearCache();
}
