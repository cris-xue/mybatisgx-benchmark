package com.benchmark.mybatisgx.mapper;

import com.benchmark.mybatisgx.entity.User;
import com.mybatisgx.dao.SimpleDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * MyBatisGX 用户 Mapper
 */
@Mapper
public interface MybatisgxUserMapper extends SimpleDao<User, User, Long> {

    List<User> findAllByIdIn(List<Long> ids);
}
