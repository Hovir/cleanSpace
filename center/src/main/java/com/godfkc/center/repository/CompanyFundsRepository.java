package com.godfkc.center.repository;

import com.godfkc.center.entity.CompanyFunds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Savepoint;
import java.util.Date;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
public interface CompanyFundsRepository extends JpaRepository<CompanyFunds, Long>{

    //根据companyId查询
    @Query("select cf from CompanyFunds cf where cf.company.id = ?1")
    CompanyFunds findCompanyFundsByCompanyId(Long companyId);


    //提现 改变余额
    @Modifying
    @Query("update CompanyFunds cf set cf.money = ?1,cf.updateTime = ?2 where cf.company.id = ?3 and cf.status<>0")
    int changeBalance(Long money, Date updateTime,Long companyId);


    //根据companyId更改funds表中money，updateTime字段 lqj add 2018-2-8
    @Modifying
    @Query("update CompanyFunds cf set cf.money = cf.money + ?1 ,cf.updateTime = ?2 where cf.company.id = ?3")
    void updateFundsMoneyAndUpdateTimeByCompanyId(Long money,Date updateTime,Long id);

}
