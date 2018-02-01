package com.godfkc.center.repository;

import com.godfkc.center.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByNameAndPassword(String name, String password);
}
