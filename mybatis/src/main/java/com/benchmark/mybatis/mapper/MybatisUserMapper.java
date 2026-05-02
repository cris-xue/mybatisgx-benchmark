package com.benchmark.mybatis.mapper;

import com.benchmark.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MyBatis 用户 Mapper
 */
@Mapper
public interface MybatisUserMapper {

    int insert(User user);

    int insertBatch(@Param("list") List<User> userList);

    int updateById(User user);

    int updateSelectiveById(User user);

    int updateBatchById(@Param("list") List<User> userList);

    User findById(@Param("id") Long id);

    User findByIdAndAgeAndStatus(@Param("id") Long id, @Param("age") Integer age, @Param("status") Integer status);

    User findByIdAndUsernameLikeAndAgeGtAndStatusIn(@Param("id") Long id, @Param("username") String username,
                                                     @Param("age") Integer age, @Param("statusList") List<Integer> statusList);

    List<User> findDynamicByIdAndUsernameLikeAndAgeGtAndStatusIn(@Param("id") Long id, @Param("username") String username,
                                                                   @Param("age") Integer age, @Param("statusList") List<Integer> statusList);
}
