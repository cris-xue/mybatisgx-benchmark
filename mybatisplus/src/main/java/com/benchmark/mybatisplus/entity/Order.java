package com.benchmark.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MyBatis-Plus 订单实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("benchmark_order")
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String orderNo;

    private BigDecimal amount;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    /**
     * 构造函数（用于批量插入）
     */
    public Order(Long userId, String orderNo, BigDecimal amount, Integer status) {
        this.userId = userId;
        this.orderNo = orderNo;
        this.amount = amount;
        this.status = status;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
