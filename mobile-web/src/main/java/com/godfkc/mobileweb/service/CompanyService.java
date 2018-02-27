package com.godfkc.mobileweb.service;

/**
 *
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
public interface CompanyService {
    boolean findByNameAndPassword(String name,String password);

    String findCompanyDetailByName(String companyName);
    String selectUnderCompany(Long id);
    String findByCompanyId(Long companyId);
    boolean unbindMod(Long bankCardId);
    String findBankDictAll();
    boolean bindBankCard(String cardNo,String phone,Long compayId,String bankDictId,String username);

    String selectCompanies(Long levelId, String state, String city, String district);
    String findCompanyFundsByCompanyId(Long companyId);
    //根据企业id查询CompanyBankCard
    String findCompanyBankCardByCompanyId(Long companyId);
    boolean changeBalance(int money,Long companyId);
    boolean insertFundsWithdraw(String withdrawlMoney,Long companyId);

    boolean insertFundsLog(int afterWithdrawInt, String withdrawlMoney, Long companyId,String descreption,Integer type);
    String findFundsLogByCompanyId(Long companyId);

    //根据companyId查询levelId
    Long selectLevelIdByCompanyId(Long companyId);
}
