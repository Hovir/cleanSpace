package com.godfkc.center.controller.admin;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyAddress;
import com.godfkc.center.entity.Level;
import com.godfkc.center.entity.vo.CompanySearchEmp;
import com.godfkc.center.service.admin.CompanyService;
import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.dataTables.ReturnedDataUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CompanyListController {
    @Autowired
    private CompanyService companyService;

    /**
     * 企业列表-查询-企业列表
     * @param sentParameters
     * @return
     */
    @RequestMapping(value = "/company/list",method = RequestMethod.POST)
    public ReturnedData getCompaniesList(@RequestBody SentParameters sentParameters){
        if (sentParameters!=null){}
        //返回到前台的数据
        ReturnedData<Company> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);
        Page<Company> page = companyService.getCompaniesList(dataTablesReturn.getPage(), dataTablesReturn.getSize(), dataTablesReturn.getDir(), dataTablesReturn.getData(), dataTablesReturn.getSearch());
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());
        return returnedData;
    }

    /**
     * 企业列表-查询-按条件查询
     * @param dateFrom
     * @param dateTo
     * @param companyName
     * @param sentParameters
     * @return
     */
    @RequestMapping(value = "/company/listSearch/{dateFrom}/{dateTo}/{companyName}",method = RequestMethod.POST)
    public ReturnedData getCompaniesListSearch(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName, @RequestBody SentParameters sentParameters){
        //获取参数
        Date fromDate=null;
        Date toDate=null;
        try {
            fromDate=this.stringToDate(dateFrom,"yyyy-MM-dd");
            toDate=this.stringToDate(dateTo,"yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //返回到前台的数据
        ReturnedData<Company> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);
        CompanySearchEmp companySearchEmp=new CompanySearchEmp();
        companySearchEmp.setDateFrom(fromDate);
        companySearchEmp.setDateTo(toDate);
        companySearchEmp.setCompanyName(companyName);
        companySearchEmp.setPage(dataTablesReturn.getPage());
        companySearchEmp.setSize(dataTablesReturn.getSize());
        companySearchEmp.setDir(dataTablesReturn.getDir());
        companySearchEmp.setData(dataTablesReturn.getData());
        Page<Company> page = companyService.getCompaniesSearch(companySearchEmp);
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());
        return returnedData;
    }

    /**
     * util-字符串转换成Date类型
     * @param dateString
     * @param dateForm
     * @return
     * @throws ParseException
     */
    public Date stringToDate(String dateString,String dateForm) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateForm);
        Date date=simpleDateFormat.parse(dateString);
        return date;
    }

    /**
     * 企业列表-添加-查询CompanyIdName
     * @return
     */
    @RequestMapping(value = "/company/Add/companyNameList")
    public List<Company> getCompanyNameList() {
        return companyService.getCompanyIdName();
    }
    /**
     * 企业列表-添加-查询LevelIdName
     * @return
     */
    @RequestMapping(value = "/company/Add/levelNameList")
    public List<Level> getLevelNameList() {
        return companyService.getLevelIdName();
    }

    /**
     * 企业列表-添加-查询Level（级联）
     * @param companyId
     * @return
     */
    @RequestMapping("/company/Add/{companyId}/levelNameList")
    public List<Level> getCompaniesLevel(@PathVariable("companyId") Long companyId){
        Company companyOne= companyService.getCompanyOneDetails(companyId);
        Long levelId=companyOne.getLevel().getId();
        List<Level> levels=companyService.getLevesByIdAfter(levelId);
        return levels;
    }
    /**
     * 企业列表-添加-添加数据（公司信息）
     * @param company
     * @return
     */
    @RequestMapping(value = "company/Add/{levelId}/{parentId}/{state}/{city}/{district}/edit",method = RequestMethod.POST)
    public Company addCompaniesList(@RequestBody Company company, @PathVariable("levelId") Long  levelId, @PathVariable("parentId") Long parentId,
                                    @PathVariable("state") String state,@PathVariable("city") String city,@PathVariable("district")String district) {
        //System.out.println("companyAddress="+state+city+district);
        Level level=new Level();
        level.setId(levelId);
        company.setLevel(level);
        Company companyParam=new Company();
        companyParam.setId(parentId);
        company.setParent(companyParam);
        Set<CompanyAddress> companyAddressesSet=new HashSet<>();
        CompanyAddress companyAddress=new CompanyAddress();
        companyAddress.setState(state);
        companyAddress.setCity(city);
        companyAddress.setDistrict(district);
        companyAddress.setCompany(company);
        companyAddressesSet.add(companyAddress);
        company.setCompanyAddresses(companyAddressesSet);
        return companyService.addCompany(company);
    }

    /**
     * 企业列表-编辑/修改-查询数据+公司地址
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/company/companyAddress/{companyId}/edit")
    public CompanyAddress getCompanyAndAddressDetail(@PathVariable("companyId") Long companyId) {
        Company company=new Company();
        company.setId(companyId);
        return companyService.getCompanyAddress(company);
    }

    /**
     * 后台列表-编辑-查询数据(无公司地址)
     * @param id
     * @return
     */
    @RequestMapping("/company/details/{id}/edit")
    public Company getCompanyOneDetail(@PathVariable("id") Long id){
        Company companyOne= companyService.getCompanyOneDetails(id);
        return companyOne;
    }

    /**
     * 企业列表-编辑-修改数据(公司信息)
     * @param company
     * @return
     */
    @RequestMapping("/company/Update/update/edit")
    public Integer updateCompaniesOneDetails(@RequestBody Company company){
        Long id=company.getId();
        String name=company.getName();
        String imgUrl=company.getImgUrl();
        String profile=company.getProfile();
        Integer companyOne=companyService.updateCompanyOneDetails(id,name,imgUrl,profile);
        return companyOne;
    }

    /**
     * 企业列表-编辑-修改数据（公司地址）
     * @param companyAddress
     * @param companyId
     * @return
     */
    @RequestMapping("/company/saveAndUpdate/adress/{companyId}/edit")
    public Integer saveAndUpdateCompaniesAddress(@RequestBody CompanyAddress companyAddress,@PathVariable("companyId") Long companyId){
        String state=companyAddress.getState();
        String city=companyAddress.getCity();
        String district=companyAddress.getDistrict();
        //System.out.println("state="+state+"city="+city+"district="+district);
        //修改
        Company company=new Company();
        company.setId(companyId);
        Integer companyAdressResult=companyService.updateCompanyAdress(state,city,district,company);
        System.out.println("=修改=="+companyAdressResult);
        //添加
        if(companyAdressResult==null||companyAdressResult==0){
            companyAdressResult=companyService.addCompanyAddress(state,city,district,company);
            System.out.println("=添加=="+companyAdressResult);
        }
        return companyAdressResult;
    }

    /**
     * 企业列表-修改-修改密码
     * @param id
     * @param password
     * @return
     */
    @RequestMapping("/company/Update/{id}/{password}/p/edit")
    public Integer updateCompaniesOnePwd(@PathVariable("id") Long id,@PathVariable("password") String password){
        Integer companyOne=companyService.updateCompanyOnePwd(id,password);
        return companyOne;
    }

    /**
     * 企业列表-删除-修改状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/company/Update/{id}/{status}/s/edit")
    public Integer updateCompaniesOneStatus(@PathVariable("id") Long id,@PathVariable("status") Integer status){
        Integer companyOne=null;
        if (status!=null){
            companyOne=companyService.updateCompanyOneStatus(id,status);
        }
        return companyOne;
    }

    /**
     * 企业列表-添加-查询公司编号（验证）
     * @param bn
     * @return
     */
    @RequestMapping("/company/get/{bn}/edit")
    public Company getCompaniesBnAndStatus(@PathVariable("bn") String bn){
        return companyService.getCompaniesByBnAndStatus(bn);
    }
}
