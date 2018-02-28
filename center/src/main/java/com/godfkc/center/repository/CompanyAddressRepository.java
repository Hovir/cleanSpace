package com.godfkc.center.repository;


import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Long>{

    //根据公司id查询地址
    CompanyAddress getCompanyAddressByCompany(Company company);
}
