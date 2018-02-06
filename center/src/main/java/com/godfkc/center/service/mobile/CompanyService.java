package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.BankDict;
import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyBankCard;

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
}
