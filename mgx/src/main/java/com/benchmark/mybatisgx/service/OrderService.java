package com.benchmark.mybatisgx.service;

import com.benchmark.mybatisgx.entity.Order;
import com.benchmark.mybatisgx.mapper.MybatisgxOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyBatisGX 测试服务
 * 封装 MyBatisGX 批量操作和关联查询方法
 */
@Service
public class OrderService {

    @Autowired
    private MybatisgxOrderMapper orderMapper;

    @Transactional
    public int batchInsert(List<Order> users) {
        return orderMapper.insertBatch(users);
    }

    @Transactional
    public int batchUpdate(List<Order> users) {
        return orderMapper.updateBatchById(users);
    }
}
