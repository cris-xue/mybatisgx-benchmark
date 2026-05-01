package com.benchmark.mybatisgx.mapper;

import com.benchmark.mybatisgx.entity.Order;
import com.mybatisgx.dao.SimpleDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatisGX 订单 Mapper
 */
@Mapper
public interface MybatisgxOrderMapper extends SimpleDao<Order, Order, Long> {
}
