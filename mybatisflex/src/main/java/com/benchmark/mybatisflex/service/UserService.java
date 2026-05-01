package com.benchmark.mybatisflex.service;

import com.benchmark.mybatisflex.entity.User;
import com.benchmark.mybatisflex.mapper.MyBatisFlexUserMapper;
import com.mybatisflex.core.row.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyBatis-Flex 测试服务
 * 封装 MyBatis-Flex 批量操作和关联查询方法
 */
@Service
public class UserService {

    @Autowired
    private MyBatisFlexUserMapper userMapper;

    @Transactional
    public int batchInsert(List<User> users) {
        return userMapper.insertBatch(users);
    }

    @Transactional
    public int batchUpdate(List<User> users) {
        Db.executeBatch(users, 1000, MyBatisFlexUserMapper.class, (mapper, user) -> mapper.update(user));
        return users.size();
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByIdsWithOrders(List<Long> ids) {
        return userMapper.selectListByIds(ids);
    }
}
