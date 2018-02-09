package com.godfkc.center.service.admin;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Level;
import com.godfkc.center.entity.vo.CompanySearchEmp;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface CompanyService {

    //后台列表-list-(启用状态ALL)
    Page<Company> getCompaniesList(Integer page, Integer size, String dir, String data, String search);

    //后台列表-list-搜索-Data
    Page<Company> getCompaniesSearch(CompanySearchEmp companySearchEmp);

    //后台列表-list-个体-添加-查询
    List<Company> getCompanyIdName();

    //后台列表-list-个体-添加-查询
    List<Level> getLevelIdName();

    //后台列表-list-个体-添加
    Company   addCompany(Company company);

    //后台列表-list-个体详情数据-查询
    Company getCompanyOneDetails(Long id);

    //后台列表-list-个体详情数据-修改-信息
    int updateCompanyOneDetails(Long id, String name, String imgUrl, String profile);

    //后台列表-list-修改-密码
    int updateCompanyOnePwd(Long id, String password);

    //后台列表-list-修改状态（0停用，1启用）
    int updateCompanyOneStatus(Long id, int status);
}
