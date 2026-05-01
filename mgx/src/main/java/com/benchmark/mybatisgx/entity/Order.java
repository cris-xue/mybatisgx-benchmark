package com.benchmark.mybatisgx.entity;

import com.mybatisgx.annotation.*;
import com.mybatisgx.executor.genval.IdValueProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.FetchType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * MyBatisGX 订单实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "benchmark_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(IdValueProcessor.class)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Fetch(FetchMode.BATCH)
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
