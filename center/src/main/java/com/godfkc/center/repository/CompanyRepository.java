package com.godfkc.center.repository;

import com.godfkc.center.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(nativeQuery = true,value = "select c.* from cs_company c where c.name=?1 and c.password=?2")
    Company findByNameAndPassword(String name, String password);

    @Query("select coy from Company coy where coy.parent.id=?1 and coy.status=1")
    List<Company> selectUnderCompany(Long companyId);

    Company findByName(String name);
}
