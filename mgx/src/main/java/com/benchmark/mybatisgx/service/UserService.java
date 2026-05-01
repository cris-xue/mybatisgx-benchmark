package com.benchmark.mybatisgx.service;

import com.benchmark.mybatisgx.entity.User;
import com.benchmark.mybatisgx.mapper.MybatisgxUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyBatisGX 测试服务
 * 封装 MyBatisGX 批量操作和关联查询方法
 */
@Service
public class UserService {

    @Autowired
    private MybatisgxUserMapper userMapper;

    @Transactional
    public int batchInsert(List<User> users) {
        return userMapper.insertBatch(users);
    }

    @Transactional
    public int batchUpdate(List<User> users) {
        return userMapper.updateBatchById(users);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByIdsWithOrders(List<Long> ids) {
        return userMapper.findAllByIdIn(ids);
    }
}
