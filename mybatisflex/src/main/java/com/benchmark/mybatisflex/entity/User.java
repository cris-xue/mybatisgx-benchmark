package com.benchmark.mybatisflex.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * MyBatis-Flex 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("benchmark_user")
public class User implements Serializable {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
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
