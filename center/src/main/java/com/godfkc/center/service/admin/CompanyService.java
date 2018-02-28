package com.godfkc.center.service.admin;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyAddress;
import com.godfkc.center.entity.Level;
import com.godfkc.center.entity.vo.CompanySearchEmp;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface CompanyService {

    //后台列表-list-(启用状态ALL)
    Page<Company> getCompaniesList(Integer page, Integer size, String dir, String data, String search);

    //后台列表-list-搜索-按条件搜索-Data
    Page<Company> getCompaniesSearch(CompanySearchEmp companySearchEmp);

    //后台列表-list-个体-添加-查询公司id、名称
    List<Company> getCompanyIdName();

    //后台列表-list-个体-添加-查询等级
    List<Level> getLevelIdName();

    //后台列表-list-个体-添加-查询等级
    List<Level> getLevesByIdAfter(Long id);

    //后台列表-list-个体-添加-查询公司编号（验证存在与否）
    Company getCompaniesByBnAndStatus(String bn);

    //后台列表-list-个体-添加-添加数据
    Company   addCompany(Company company);

    //后台列表-list-个体-详情-查询公司信息（无公司地址）
    Company getCompanyOneDetails(Long id);

    //后台列表-list-个体-详情/修改-查询数据+公司地址
    CompanyAddress getCompanyAddress(Company company);

    //后台列表-list-个体-修改-修改数据
    int updateCompanyOneDetails(Long id, String name, String imgUrl, String profile);

    //后台列表-list-个体-修改-修改密码
    int updateCompanyOnePwd(Long id, String password);

    //后台列表-list-个体-删除-修改状态（0停用，1启用）
    int updateCompanyOneStatus(Long id, int status);
}
