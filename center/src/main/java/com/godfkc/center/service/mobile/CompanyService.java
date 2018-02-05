package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Company;

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
}
