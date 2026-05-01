package com.benchmark.mybatisflex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationManyToOne;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MyBatis-Flex 订单实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("benchmark_order")
public class Order implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private Long userId;

    private String orderNo;

    private BigDecimal amount;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @RelationManyToOne(selfField = "userId", targetField = "id")
    private User user;

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
