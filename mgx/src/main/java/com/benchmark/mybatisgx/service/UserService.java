package com.benchmark.mybatisgx.service;

import com.benchmark.mybatisgx.entity.User;
import com.benchmark.mybatisgx.mapper.MybatisgxUserMapper;
import com.mybatisgx.executor.page.Pageable;
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
    public int batchInsert(List<User> userList) {
        return userMapper.insertBatch(userList);
    }

    @Transactional
    public int batchUpdate(List<User> userList) {
        return userMapper.updateBatchById(userList);
    }

    @Transactional
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Transactional
    public int updateSelectiveById(User user) {
        return userMapper.updateSelectiveById(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Transactional(readOnly = true)
    public User findByIdAndAgeAndStatus(Long id, Integer age, Integer status) {
        return userMapper.findByIdAndAgeAndStatus(id, age, status);
    }

    @Transactional(readOnly = true)
    public User findByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String name, Integer age, List<Integer> status) {
        return userMapper.findByIdAndUsernameLikeAndAgeGtAndStatusIn(id, name, age, status);
    }

    @Transactional(readOnly = true)
    public List<User> findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String name, Integer age, List<Integer> status) {
        return userMapper.findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(id, name, age, status);
    }

    @Transactional(readOnly = true)
    public List<User> findPage(User user) {
        return userMapper.findPage(user, new Pageable(1, 10)).getList();
    }
}
