package com.benchmark.mybatisflex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationOneToMany;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * MyBatis-Flex 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("benchmark_user")
public class User implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String username;

    private String email;

    private String phone;

    private Integer age;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @RelationOneToMany(selfField = "id", targetField = "userId")
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
