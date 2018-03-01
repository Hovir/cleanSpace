package com.godfkc.center.repository;


import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    //根据公司id修改地址
    @Modifying
    @Query("UPDATE CompanyAddress SET state=:state,city=:city,district=:district WHERE company=:company")
    int updateCompanyAddressByCompany(@Param("state") String state, @Param("city") String city, @Param("district") String district, @Param("company") Company company);
}
