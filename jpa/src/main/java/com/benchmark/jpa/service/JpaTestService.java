package com.benchmark.jpa.service;

import com.benchmark.jpa.entity.User;
import com.benchmark.jpa.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA 测试服务
 * 封装 JPA 批量操作和关联查询方法
 */
@Service
public class JpaTestService {

    @Autowired
    private JpaUserRepository userRepository;

    /**
     * 批量插入用户
     */
    @Transactional
    public List<User> batchInsertUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    /**
     * 批量更新用户
     */
    @Transactional
    public List<User> batchUpdateUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    /**
     * 根据 ID 列表查询用户及其订单
     */
    @Transactional(readOnly = true)
    public List<User> findUsersByIdsWithOrders(List<Long> ids) {
        return userRepository.findAllById(ids);
    }
}
