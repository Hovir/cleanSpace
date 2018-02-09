package com.godfkc.center.controller.admin;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Level;
import com.godfkc.center.entity.vo.CompanySearchEmp;
import com.godfkc.center.service.admin.CompanyService;
import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.dataTables.ReturnedDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CompanyListController {
    @Autowired
    private CompanyService companyService;

    /**
     * 后台查询-list-ALL
     * @param sentParameters
     * @return
     */
    @RequestMapping(value = "/company/list",method = RequestMethod.POST)
    public ReturnedData getCompaniesList(@RequestBody SentParameters sentParameters){
        System.out.println("sssssssss");
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
     * 后台查询-list-Search
     * @param dateFrom
     * @param dateTo
     * @param companyName
     * @param sentParameters
     * @return
     */
    @RequestMapping(value = "/company/listSearch/{dateFrom}/{dateTo}/{companyName}",method = RequestMethod.POST)
    public ReturnedData getCompaniesListSearch(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName, @RequestBody SentParameters sentParameters){
        System.out.println("dateFrom="+dateFrom);
        System.out.println("dateTo="+dateTo);
        System.out.println("companyName="+companyName);

        //获取参数
        System.out.println("===="+sentParameters);
        //SentParameters sentParameters=(SentParameters)mapSearch.get("sentParameters");
       /* String dateFrom=(String)mapSearch.get("dateFrom");
        String dateTo=(String)mapSearch.get("dateTo");
        String companyName=(String)mapSearch.get("companyName");*/
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
     * 后台-添加-查询数据
     * @return
     */
    @RequestMapping(value = "/company/Add/companyNameList")
    public List<Company> getCompanyNameList() {
        return companyService.getCompanyIdName();
    }
    /**
     * 后台-添加-查询数据
     * @return
     */
    @RequestMapping(value = "/company/Add/levelNameList")
    public List<Level> getLevelNameList() {
        return companyService.getLevelIdName();
    }
    /**
     * 后台-添加-添加数据
     * @param company
     * @return
     */
    @RequestMapping(value = "/company/Add/{levelId}/{parentId}/edit",method = RequestMethod.POST)
    public Company addCompaniesList(@RequestBody Company company,@PathVariable("levelId") Long  levelId,@PathVariable("parentId") Long parentId) {
        System.out.println("添加-companyName="+company.getName());
        System.out.println("添加-imgUrl="+company.getImgUrl());
        System.out.println("添加-profile="+company.getProfile());
        System.out.println("添加-bn="+company.getBn());
        System.out.println("添加-password="+company.getPassword());
        System.out.println("添加-levelId="+levelId);
        System.out.println("添加-parentId="+parentId);
        Level level=new Level();
        level.setId(levelId);
        company.setLevel(level);
        Company companyParam=new Company();
        companyParam.setId(parentId);
        company.setParent(companyParam);
        return companyService.addCompany(company);
    }

    /**
     * 后台列表-list-编辑-查询数据
     * @param id
     * @return
     */
    @RequestMapping("/company/details/{id}/edit")
    public Company getCompaniesOneDetails(@PathVariable("id") Long id){
        System.out.println("=="+id);
        Company companyOne= companyService.getCompanyOneDetails(id);
        System.out.println("name="+companyOne.getName());
        return companyOne;
    }

    /**
     * 后台列表-list-编辑-修改数据
     * @param company
     * @return
     */
    @RequestMapping("/company/Update/update/edit")
    public Integer updateCompaniesOneDetails(@RequestBody Company company){
        Long id=company.getId();
        String name=company.getName();
        String imgUrl=company.getImgUrl();
        String profile=company.getProfile();

        System.out.println("id="+company.getId());
        System.out.println("name="+company.getName());
        System.out.println("imgUrl="+company.getImgUrl());
        System.out.println("profile="+company.getProfile());
        Integer companyOne=companyService.updateCompanyOneDetails(id,name,imgUrl,profile);
        System.out.println("INT="+companyOne);
        return companyOne;
    }

    /**
     * 后台查询-list-pwd-修改密码
     * @param id
     * @param password
     * @return
     */
    @RequestMapping("/company/Update/{id}/{password}/p/edit")
    public Integer updateCompaniesOnePwd(@PathVariable("id") Long id,@PathVariable("password") String password){
        System.out.println("=="+id);
        Integer companyOne=companyService.updateCompanyOnePwd(id,password);
        System.out.println("INT="+companyOne);
        return companyOne;
    }

    /**
     * 后台查询-list-删除-修改状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/company/Update/{id}/{status}/s/edit")
    public Integer updateCompaniesOneStatus(@PathVariable("id") Long id,@PathVariable("status") Integer status){
        System.out.println("=="+id);
        Integer companyOne=null;
        if (status!=null){
            companyOne=companyService.updateCompanyOneStatus(id,status);
        }
        System.out.println("INT="+companyOne);
        return companyOne;
    }



}
