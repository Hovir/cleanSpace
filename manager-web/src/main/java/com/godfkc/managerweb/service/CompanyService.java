package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

public interface CompanyService {

    //后台查询-list-(启用状态所用企业)
    String  getCompaniesList(SentParameters sentParameters);

    //后台查询-list-搜索-Data
    String  getCompaniesSearch(SentParameters sentParameters, String dateFrom, String dateTo, String companyName);

    //后台查询-list-个体编辑-查询
    String  getCompanyOneDetails(Long id);

    //后台查询-list-个体编辑-修改
    String  updateCompanyOneDetails(Long id, String companyName, String companyUrl, String companyProfile);

    //后台查询-list-修改-密码
    String  updateCompanyOnePwd(Long id, String password);

    //后台查询-list-修改状态（0停用，1启用）
    String  updateCompanyOneStatus(Long id, int status);
}
