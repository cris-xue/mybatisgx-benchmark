package com.benchmark.mybatis.service;

import com.benchmark.mybatis.entity.User;
import com.benchmark.mybatis.mapper.MybatisUserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyBatis 测试服务
 * 封装 MyBatis 批量操作和查询方法
 */
@Service
public class UserService {

    @Autowired
    private MybatisUserMapper userMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Transactional
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Transactional
    public int batchInsert(List<User> userList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            MybatisUserMapper userMapper = sqlSession.getMapper(MybatisUserMapper.class);
            for (int i = 0; i < userList.size(); i++) {
                userMapper.insert(userList.get(i));
                if ((i + 1) % 1000 == 0 || (i + 1) == userList.size()) {
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return userList.size();
    }

    @Transactional
    public int updateById(User user) {
        return userMapper.updateById(user);
    }

    @Transactional
    public int updateSelectiveById(User user) {
        return userMapper.updateSelectiveById(user);
    }

    @Transactional
    public int batchUpdate(List<User> userList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            MybatisUserMapper userMapper = sqlSession.getMapper(MybatisUserMapper.class);
            for (int i = 0; i < userList.size(); i++) {
                userMapper.updateById(userList.get(i));
                if ((i + 1) % 1000 == 0 || (i + 1) == userList.size()) {
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return userList.size();
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
    public User findByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String username, Integer age, List<Integer> status) {
        return userMapper.findByIdAndUsernameLikeAndAgeGtAndStatusIn(id, username, age, status);
    }

    @Transactional(readOnly = true)
    public List<User> findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String username, Integer age, List<Integer> status) {
        return userMapper.findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(id, username, age, status);
    }
}
