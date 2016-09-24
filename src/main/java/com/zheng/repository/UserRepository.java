package com.zheng.repository;

import com.zheng.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by zhenglian on 2016/9/24.
 */
public interface UserRepository extends CrudRepository<User, Serializable> {
}
