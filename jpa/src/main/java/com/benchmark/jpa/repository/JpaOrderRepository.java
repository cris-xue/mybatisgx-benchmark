package com.benchmark.jpa.repository;

import com.benchmark.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA 订单 Repository
 */
@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
