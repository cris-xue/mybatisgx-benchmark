package com.benchmark.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * JPA 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "benchmark_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 20)
    private String phone;

    private Integer age;

    @Column(nullable = false)
    private Integer status;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    /**
     * 构造函数（用于批量插入）
     */
    public User(String username, String email, String phone, Integer age, Integer status) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.status = status;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
