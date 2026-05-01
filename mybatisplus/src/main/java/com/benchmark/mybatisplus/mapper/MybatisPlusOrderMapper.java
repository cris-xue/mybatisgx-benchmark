package com.benchmark.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benchmark.mybatisplus.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis-Plus 订单 Mapper
 */
@Mapper
public interface MybatisPlusOrderMapper extends BaseMapper<Order> {
}
