package com.godfkc.center.repository;

import com.godfkc.center.entity.CompanyFundsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
public interface CompanyFundsLogRepository extends JpaRepository<CompanyFundsLog, Long>{

    @Query("select cfl from CompanyFundsLog cfl where cfl.company.id = ?1 order by cfl.updateTime desc")
    List<CompanyFundsLog> findAllByCompanyId (Long companyId);
}
