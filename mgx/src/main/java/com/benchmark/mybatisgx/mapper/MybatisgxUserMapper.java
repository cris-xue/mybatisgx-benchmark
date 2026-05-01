package com.benchmark.mybatisgx.mapper;

import com.benchmark.mybatisgx.entity.User;
import com.mybatisgx.annotation.Dynamic;
import com.mybatisgx.dao.SimpleDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MyBatisGX 用户 Mapper
 */
@Mapper
public interface MybatisgxUserMapper extends SimpleDao<User, User, Long> {

    User findByIdAndAgeAndStatus(Long id, Integer age, Integer status);

    User findByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String name, Integer age, List<Integer> status);

    @Dynamic
    List<User> findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(Long id, String name, Integer age, List<Integer> status);
}
