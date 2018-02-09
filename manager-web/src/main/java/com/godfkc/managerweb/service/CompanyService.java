package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

import java.util.Map;

public interface CompanyService {

    //后台查询-list-(启用状态所用企业)
    String  getCompaniesList(SentParameters sentParameters);

    //后台查询-list-搜索-Data
    String  getCompaniesSearch(SentParameters sentParameters, String dateFrom, String dateTo, String companyName);

    //后台查询-list-个体-查询公司名称
    String  getCompanyNameList();

    //后台查询-list-个体-查询级别名称
    String  getLevelNameList();

    //后台查询-list-个体-添加
    String  insertCompany(Map<String, Object> map,Long levelId,Long parentId);

    //后台查询-list-个体编辑-查询
    String  getCompanyOneDetails(Long id);

    //后台查询-list-个体编辑-修改
    String  updateCompanyOneDetails(Map<String, Object> map);

    //后台查询-list-修改-密码
    String  updateCompanyOnePwd(Long id, String password);

    //后台查询-list-修改状态（0停用，1启用）
    String  updateCompanyOneStatus(Long id, int status);
}
