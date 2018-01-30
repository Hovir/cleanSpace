package com.godfkc.center.repository;

import com.godfkc.center.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
public interface CardRepository extends JpaRepository<Card, Long> {
}
