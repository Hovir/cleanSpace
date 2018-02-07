package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.*;

import java.util.Date;
import java.util.List;

/**
 *
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
public interface CompanyService {
    Company findByNameAndPassword(String name, String password);

    List<Company> selectUnderCompany(Long companyId);
    Company findByName(String name);

    CompanyBankCard findByCompanyId(Long companyId);

    int unbindMod(Long bankCardId);

    List<BankDict> findBankDictAll();

    //绑定银行卡
    CompanyBankCard bindBankCard(CompanyBankCard companyBankCard);

    List<Company> selectCompanies(Long levelId, String state, String city, String district);
    //根据企业id查询company_funds
    CompanyFunds findCompanyFundsByCompanyId(Long companyId);
    //根据企业id查询CompanyBankCard
    CompanyBankCard findCompanyBankCardByCompanyId(Long companyId);
    //提现 改变余额
    int changeBalance(Long money, Date updateTime, Long companyId);
    //插入提现表
    CompanyFundsWithdraw insertFundsWithdraw(CompanyFundsWithdraw companyFundsWithdraw);

    CompanyFundsLog insertFundsLog(CompanyFundsLog companyFundsLog);
}
