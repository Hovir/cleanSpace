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
}
