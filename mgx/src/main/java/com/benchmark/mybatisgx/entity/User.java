package com.benchmark.mybatisgx.entity;

import com.mybatisgx.annotation.Entity;
import com.mybatisgx.annotation.GeneratedValue;
import com.mybatisgx.annotation.Id;
import com.mybatisgx.annotation.Table;
import com.mybatisgx.executor.genval.IdValueProcessor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
