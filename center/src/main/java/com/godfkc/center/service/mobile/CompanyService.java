package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Company;

/**
 *
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
public interface CompanyService {
    Company findByNameAndPassword(String name, String password);
    Company findByName(String name);
}
