package com.godfkc.center.repository;

import com.godfkc.center.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
