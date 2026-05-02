package com.benchmark.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benchmark.mybatisplus.entity.User;
import com.benchmark.mybatisplus.mapper.MybatisPlusUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyBatis-Plus 测试服务
 * 封装 MyBatis-Plus 批量操作和查询方法
 */
@Service
public class MybatisPlusTestService extends ServiceImpl<MybatisPlusUserMapper, User> implements IService<User> {

    @Transactional
    public int insert(User user) {
        return baseMapper.insert(user);
    }

    @Transactional
    public int batchInsert(List<User> userList) {
        super.saveBatch(userList, 1000);
        return userList.size();
    }

    @Transactional
    public boolean updateById(User user) {
        baseMapper.updateById(user);
        return true;
    }

    @Transactional
    public int updateSelectiveById(User user) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId, user.getId());
        if (user.getUsername() != null) {
            wrapper.set(User::getUsername, user.getUsername());
        }
        if (user.getEmail() != null) {
            wrapper.set(User::getEmail, user.getEmail());
        }
        if (user.getPhone() != null) {
            wrapper.set(User::getPhone, user.getPhone());
        }
        if (user.getAge() != null) {
            wrapper.set(User::getAge, user.getAge());
        }
        if (user.getStatus() != null) {
            wrapper.set(User::getStatus, user.getStatus());
        }
        if (user.getUpdateTime() != null) {
            wrapper.set(User::getUpdateTime, user.getUpdateTime());
        }
        return baseMapper.update(null, wrapper);
    }

    @Transactional
    public int batchUpdate(List<User> userList) {
        super.updateBatchById(userList, 1000);
        return userList.size();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return baseMapper.selectById(id);
    }

    @Transactional(readOnly = true)
    public User findByIdAndAgeAndStatus(Long id, Integer age, Integer status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id)
                .eq(User::getAge, age)
                .eq(User::getStatus, status);
        return baseMapper.selectOne(wrapper);
    }

    @Transactional(readOnly = true)
    public User findByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String username, Integer age, List<Integer> status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id)
                .like(User::getUsername, username)
                .gt(User::getAge, age)
                .in(User::getStatus, status);
        return baseMapper.selectOne(wrapper);
    }

    @Transactional(readOnly = true)
    public List<User> findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String username, Integer age, List<Integer> status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (id != null) {
            wrapper.eq(User::getId, id);
        }
        if (username != null) {
            wrapper.like(User::getUsername, username);
        }
        if (age != null) {
            wrapper.gt(User::getAge, age);
        }
        if (status != null && !status.isEmpty()) {
            wrapper.in(User::getStatus, status);
        }
        return baseMapper.selectList(wrapper);
    }
}
