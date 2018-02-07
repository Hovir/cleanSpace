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
}
