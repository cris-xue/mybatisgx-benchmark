package com.benchmark.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String email;

    @NotNull
    private Long phone;

    @NotNull
    private Integer age;

    @NotNull
    private Integer status;

    @NotNull
    private Date createTime;

    @NotNull
    private Date updateTime;
}
