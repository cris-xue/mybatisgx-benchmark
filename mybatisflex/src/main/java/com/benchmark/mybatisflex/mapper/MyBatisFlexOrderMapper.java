package com.benchmark.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.benchmark.mybatisflex.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis-Flex 订单 Mapper
 */
@Mapper
public interface MyBatisFlexOrderMapper extends BaseMapper<Order> {
}
