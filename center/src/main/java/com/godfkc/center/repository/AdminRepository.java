package com.godfkc.center.repository;

import com.godfkc.center.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    //查询用户信息
    Admin findByNameAndPassword(String name,String password);
}
