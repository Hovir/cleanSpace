package com.godfkc.center.repository;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyFundsWithdraw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
public interface CompanyFundsWithdrawRepository extends JpaRepository<CompanyFundsWithdraw, Long>{
    Page<CompanyFundsWithdraw> findWithdrawListByStatus(int i, Pageable pageable);

    @Modifying
    @Query("update CompanyFundsWithdraw c set c.isPay = 1 where c.id = ?1")
    void updateIsPayById(Long id);
}
