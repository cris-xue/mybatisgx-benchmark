package com.benchmark.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benchmark.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis-Plus 用户 Mapper
 */
@Mapper
public interface MybatisPlusUserMapper extends BaseMapper<User> {
}
