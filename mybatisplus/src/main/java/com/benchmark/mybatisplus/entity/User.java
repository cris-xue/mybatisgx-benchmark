package com.benchmark.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * MyBatis-Plus 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("benchmark_user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String email;

    private Long phone;

    private Integer age;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    /**
     * 构造函数（用于批量插入）
     */
    public User(String username, String email, Long phone, Integer age, Integer status) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.status = status;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
