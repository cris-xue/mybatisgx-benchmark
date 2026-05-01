package com.benchmark.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.benchmark.mybatisflex.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis-Flex 用户 Mapper
 */
@Mapper
public interface MyBatisFlexUserMapper extends BaseMapper<User> {
}
