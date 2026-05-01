package com.benchmark.mybatisgx.entity;

import com.mybatisgx.annotation.*;
import com.mybatisgx.executor.genval.IdValueProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.FetchType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * MyBatisGX 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "benchmark_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(IdValueProcessor.class)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private Integer age;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Fetch(FetchMode.BATCH)
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
