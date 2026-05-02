package com.benchmark.mybatisflex.service;

import com.benchmark.mybatisflex.entity.User;
import com.benchmark.mybatisflex.mapper.MyBatisFlexUserMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.update.UpdateChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.benchmark.mybatisflex.entity.table.UserTableDef.USER;

/**
 * MyBatis-Flex 测试服务
 * 封装 MyBatis-Flex 批量操作和查询方法
 */
@Service
public class UserService {

    @Autowired
    private MyBatisFlexUserMapper userMapper;

    @Transactional
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Transactional
    public int batchInsert(List<User> users) {
        Db.executeBatch(users, 1000, MyBatisFlexUserMapper.class, (mapper, user) -> mapper.insert(user));
        return users.size();
    }

    @Transactional
    public int updateById(User user) {
        return userMapper.update(user);
    }

    @Transactional
    public int updateSelectiveById(User user) {
        UpdateChain<User> updateChain = UpdateChain.of(User.class).where(USER.ID.eq(user.getId()));

        if (user.getUsername() != null) {
            updateChain.set(USER.USERNAME, user.getUsername());
        }
        if (user.getEmail() != null) {
            updateChain.set(USER.EMAIL, user.getEmail());
        }
        if (user.getPhone() != null) {
            updateChain.set(USER.PHONE, user.getPhone());
        }
        if (user.getAge() != null) {
            updateChain.set(USER.AGE, user.getAge());
        }
        if (user.getStatus() != null) {
            updateChain.set(USER.STATUS, user.getStatus());
        }
        if (user.getUpdateTime() != null) {
            updateChain.set(USER.UPDATE_TIME, user.getUpdateTime());
        }

        updateChain.update();
        return 1;
    }

    @Transactional
    public int batchUpdate(List<User> users) {
        Db.executeBatch(users, 1000, MyBatisFlexUserMapper.class, (mapper, user) -> mapper.update(user));
        return users.size();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userMapper.selectOneById(id);
    }

    @Transactional(readOnly = true)
    public User findByIdAndAgeAndStatus(Long id, Integer age, Integer status) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(USER.ID.eq(id))
                .and(USER.AGE.eq(age))
                .and(USER.STATUS.eq(status))
                .limit(1);
        return userMapper.selectOneByQuery(wrapper);
    }

    @Transactional(readOnly = true)
    public User findByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String username, Integer age, List<Integer> status) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(USER.ID.eq(id))
                .and(USER.USERNAME.like(username))
                .and(USER.AGE.gt(age))
                .and(USER.STATUS.in(status))
                .limit(1);
        return userMapper.selectOneByQuery(wrapper);
    }

    @Transactional(readOnly = true)
    public List<User> findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String username, Integer age, List<Integer> status) {
        QueryWrapper wrapper = QueryWrapper.create();
        if (id != null) {
            wrapper.where(USER.ID.eq(id));
        }
        if (username != null) {
            wrapper.and(USER.USERNAME.like(username));
        }
        if (age != null) {
            wrapper.and(USER.AGE.gt(age));
        }
        if (status != null && !status.isEmpty()) {
            wrapper.and(USER.STATUS.in(status));
        }
        return userMapper.selectListByQuery(wrapper);
    }
}
