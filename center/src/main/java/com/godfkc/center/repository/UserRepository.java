package com.godfkc.center.repository;

import com.godfkc.center.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface UserRepository extends JpaRepository<User,Long>{
}
