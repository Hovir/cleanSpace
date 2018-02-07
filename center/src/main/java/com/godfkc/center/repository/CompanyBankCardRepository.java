package com.godfkc.center.repository;

import com.godfkc.center.entity.CompanyBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 14:54 2018/2/5
 **/
public interface CompanyBankCardRepository extends JpaRepository<CompanyBankCard,Long> {

    @Query("select cbc from CompanyBankCard cbc where cbc.company.id = ?1 and cbc.status<>0")
    CompanyBankCard findByCompanyId(Long companyId);

    //解除银行卡绑定
    @Modifying
    @Query("update CompanyBankCard cbc set cbc.status = 0 where cbc.id = ?1")
    int unbindMod(Long bankCardId);


    //根据companyId查询
    @Query("select cbc from CompanyBankCard cbc where cbc.company.id = ?1 and cbc.status<>0")
    CompanyBankCard findCompanyBankCardByCompanyId(Long companyId);

}
