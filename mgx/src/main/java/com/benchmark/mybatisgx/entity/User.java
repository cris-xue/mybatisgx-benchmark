package com.benchmark.mybatisgx.entity;

import com.mybatisgx.annotation.*;
import com.mybatisgx.executor.genval.IdValueProcessor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @NotNull
    @Column(name = "username")
    private String username;

    @NotBlank
    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone")
    private Long phone;

    @NotNull
    @Column(name = "age")
    private Integer age;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @NotNull
    @Column(name = "create_time")
    private Date createTime;

    @NotNull
    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Fetch(FetchMode.BATCH)
    private List<Order> orders;
}
