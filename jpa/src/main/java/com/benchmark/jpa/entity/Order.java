package com.benchmark.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JPA 订单实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "benchmark_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_no", length = 50, nullable = false)
    private String orderNo;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer status;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
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
