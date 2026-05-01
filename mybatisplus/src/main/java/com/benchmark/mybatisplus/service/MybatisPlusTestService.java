package com.benchmark.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benchmark.mybatisplus.entity.User;
import com.benchmark.mybatisplus.mapper.MybatisPlusUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyBatis-Plus 测试服务
 * 封装 MyBatis-Plus 批量操作方法
 * 注意：MyBatis-Plus 不支持自动关联查询，因此不实现关联查询方法
 */
@Service
public class MybatisPlusTestService extends ServiceImpl<MybatisPlusUserMapper, User> implements IService<User> {

    @Autowired
    private MybatisPlusUserMapper userMapper;

    @Transactional
    public int batchInsertUsers(List<User> userList) {
        super.saveBatch(userList);
        return userList.size();
    }

    @Transactional
    public int batchUpdateUsers(List<User> userList) {
        super.saveOrUpdateBatch(userList);
        return userList.size();
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByIdsWithOrders(List<Long> ids) {
        throw new UnsupportedOperationException("不支持");
    }
}
