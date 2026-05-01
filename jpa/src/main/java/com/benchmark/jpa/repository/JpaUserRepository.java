package com.benchmark.jpa.repository;

import com.benchmark.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA 用户 Repository
 */
@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {
}
