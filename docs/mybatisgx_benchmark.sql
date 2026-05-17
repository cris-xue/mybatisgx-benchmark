CREATE TABLE `benchmark_user`
(
    `id`          BIGINT       NOT NULL COMMENT '主键ID（应用层生成）',
    `username`    VARCHAR(50)  NOT NULL COMMENT '用户名',
    `email`       VARCHAR(100) NOT NULL COMMENT '邮箱',
    `phone`       BIGINT       NOT NULL COMMENT '手机号',
    `age`         INT          NOT NULL COMMENT '年龄',
    `status`      INT          NOT NULL COMMENT '状态',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户基准测试表';