package com.godfkc.center.repository;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(nativeQuery = true, value = "select c.* from cs_company c where c.name=?1 and c.password=?2")
    Company findByNameAndPassword(String name, String password);

    @Query("select coy from Company coy where coy.parent.id=?1 and coy.status=1")
    List<Company> selectUnderCompany(Long companyId);

    Company findByName(String name);

    Company findById(long id);

    //后台

    //后台查询-list-分页-all
    Page<Company> findCompaniesByStatus(Integer status, Pageable pageable);

    //后台查询-list-搜索-分页-数据
    Page<Company> findCompaniesByCreateTimeBetweenAndNameAndStatus(Date from, Date to, String name, Integer status, Pageable pageable);


    //后台查询-list-编辑数据-查询
    Company findCompanyById(Long id);


    //后台查询-list-添加-save

    //后台查询-list-修改-信息
    @Modifying
    @Query("UPDATE Company SET name=:name,imgUrl=:imgUrl,profile=:profile WHERE id=:id")
    int updateCompanyById(@Param("id") Long id, @Param("name") String name, @Param("imgUrl") String imgUrl, @Param("profile") String profile);


    //后台查询-list-修改-密码
    @Modifying
    @Query("UPDATE Company SET password=:password  WHERE id=:id")
    int updateCompanyPwdById(@Param("id") Long id, @Param("password") String password);

    //后台查询-list-修改状态（0停用，1启用）
    @Modifying
    @Query("UPDATE Company SET status=:status  WHERE id=:id")
    int updateCompanyStatusById(@Param("id") Long id, @Param("status") int status);



    @Query("select coy from Company coy where coy.level.id=?1 and coy.status=1")
    List<Company> selectCompanyByLevelId(Long levelId);

    List<Company> findByStatus(Integer status);

   /* @Query(value ="SELECT csc.* FROM cs_company csc INNER JOIN cs_company_address cca ON csc.`id`=cca.`company_id` WHERE csc.`level_id`=?1 AND cca.`state`=?2",nativeQuery = true)*/
    @Query("select coy from Company coy join coy.companyAddresses ca where coy.level.id=?1 and ca.state=?2 and coy.status=1")
    List<Company> selectCompanyByLevelIdByState(Long levelId,String state);

   @Query("select coy from Company coy join coy.companyAddresses ca where coy.level.id=?1 and ca.state=?2 and ca.city=?3 and coy.status=1")
    List<Company> selectCompanyByLevelIdByStateByCity(Long levelId,String state,String city);

    @Query("select coy from Company coy join coy.companyAddresses ca where coy.level.id=?1 and ca.state=?2 and ca.city=?3 and ca.district=?4 and coy.status=1")
    List<Company> selectCompanyByLevelIdByStateByCityByDistrict(Long levelId,String state,String city,String district);

    @Query("select coy from Company coy join coy.companyAddresses ca where ca.state=?1 and coy.status=1")
    List<Company> selectCompanyByState(String state);

    @Query("select coy from Company coy join coy.companyAddresses ca where ca.state=?1 and ca.city=?2 and coy.status=3")
    List<Company> selectCompanyByStateByCity(String state, String city);

    @Query("select coy from Company coy join coy.companyAddresses ca where ca.state=?1 and ca.city=?2 and ca.district=?3 and coy.status=1")
    List<Company> selectCompanyByStateByCityByDistrict(String state, String city, String district);

    long countByStatusAfter(Integer status);
}
