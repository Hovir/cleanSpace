package com.godfkc.managerweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.managerweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 企业-列表-数据
     * @param sentParameters
     * @return
     */
    @RequestMapping("/admin/compList")
    @ResponseBody
    public String getCompany(SentParameters sentParameters){
        return companyService.getCompaniesList(sentParameters);
    }


    /**
     * 企业列表-条件搜索-页面
     * @param dateFrom
     * @param dateTo
     * @param companyName
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compSearch/{dateFrom}/{dateTo}/{companyName}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public String getSearchPage(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName,Model model) {
        System.out.println("dateFrom1="+dateFrom);
        System.out.println("dateTo1="+dateTo);
        System.out.println("companyName1="+companyName);
        model.addAttribute("dateFrom",dateFrom);
        model.addAttribute("dateTo",dateTo);
        model.addAttribute("companyName",companyName);
        return "company/companyListSearch";
    }

    /**
     * 企业列表-条件搜索-数据
     * @param sentParameters
     * @param dateFrom
     * @param dateTo
     * @param companyName
     * @return
     */
    @RequestMapping("/admin/compSearchData/{dateFrom}/{dateTo}/{companyName}")
    @ResponseBody
    public String getCompanySearchData(SentParameters sentParameters, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName){
        System.out.println("dateFrom="+dateFrom);
        System.out.println("dateTo="+dateTo);
        System.out.println("companyName="+companyName);
        return companyService.getCompaniesSearch(sentParameters,dateFrom,dateTo,companyName);
    }



    /**
     * 企业列表-添加信息-页面-公司名称
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/add/companyName.ajax")
    @ResponseBody
    public String getCompanyName() {
        String companyNameList=companyService.getCompanyNameList();
        //System.out.println("companyNameList="+companyNameList);
        //return "company/companyListAdd";
        return companyNameList;
    }

    /**
     * 企业列表-添加信息-页面-公司级别
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/add/levelName.ajax")
    @ResponseBody
    public String getLevelName() {
        String serviceLevelNameList=companyService.getLevelNameList();
        //System.out.println("serviceLevelNameList="+serviceLevelNameList);
        //return "company/companyListAdd";
        return serviceLevelNameList;
    }

    /**
     * 企业列表-添加信息-页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/add/{num}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyAddPage(@PathVariable("num") String num,Model model){
        System.out.println("添加-num="+num);
        if (("failer").equals(num)){
            model.addAttribute("failer","保存并提交失败！");
        }
        if (("sucess").equals(num)){
            model.addAttribute("failer","保存并提交成功！");
        }
        return "company/companyListAdd";
    }
    /**
     * 企业列表-添加信息-数据
     * @param companyName
     * @param imgUrl
     * @param levelId
     * @param profile
     * @return
     */
    @RequestMapping(value = "/admin/compListAddData/add/edit",method = RequestMethod.POST)
    public String getCompanyAddData(@RequestParam("companyName") String companyName,@RequestParam("imgUrl") String imgUrl,@RequestParam("levelId") Long levelId,@RequestParam("profile") String profile,@RequestParam("bn") String bn,@RequestParam("password") String password,@RequestParam("parentId") Long parentId){
        System.out.println("添加-companyName="+companyName);
        System.out.println("添加-imgUrl="+imgUrl);
        System.out.println("添加-profile="+profile);
        System.out.println("添加-bn="+bn);
        System.out.println("添加-password="+password);
        System.out.println("添加-levelId="+levelId);
        System.out.println("添加-parentId="+parentId);
        Map<String, Object> map = new HashMap<>();
        map.put("name", companyName);
        map.put("imgUrl", imgUrl);
        map.put("profile", profile);
        map.put("bn", bn);
        map.put("password", password);
        String str=companyService.insertCompany(map,levelId,parentId);
        System.out.println("添加-str="+str);
        if(str!=null){
            //成功-""
            return "/admin/compListAdd/add/sucess/edit";
        }
        //失败-""
       return "/admin/compListAdd/add/failer/edit";
    }


    /**
     * 企业列表-详情-页面（显示数据）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListShow/{id}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyShowPage(@PathVariable("id") Long id,Model model){
        System.out.println("编辑id="+id);
        String companyOneDetails=companyService.getCompanyOneDetails(id);
        Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
        System.out.println("editmap-name="+editmap.get("name"));
        System.out.println("editmap-imgUrl="+editmap.get("imgUrl"));
        System.out.println("editmap-profile="+editmap.get("profile"));
        model.addAttribute("companyShow",editmap);
        return "company/companyListShow";
    }

    /**
     * 企业列表-编辑-页面（显示数据）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListEdit/{id}/{num}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyEditPage(@PathVariable("id") Long id,@PathVariable("num") Long num,Model model){
        System.out.println("编辑id="+id);
        System.out.println("编辑num="+num);
        String companyOneDetails=companyService.getCompanyOneDetails(id);
        Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
        System.out.println("editmap-name="+editmap.get("name"));
        System.out.println("editmap-imgUrl="+editmap.get("imgUrl"));
        System.out.println("editmap-profile="+editmap.get("profile"));
        model.addAttribute("companyEdit",editmap);
        if (num==10){
            model.addAttribute("fail","保存并提交失败！");
        }
        return "company/companyListEdit";
    }

    /**
     * 企业列表-添加信息-数据
     * @param companyName
     * @param imgUrl
     * @param profile
     * @return
     */
    @RequestMapping(value = "/admin/compListEditData/update/edit",method = RequestMethod.POST)
    public String getCompanyEditData(@RequestParam("id") Long id,@RequestParam("companyName") String companyName,@RequestParam("imgUrl") String imgUrl,@RequestParam("profile") String profile){
        System.out.println("修改-id="+id);
        System.out.println("修改-companyName="+companyName);
        System.out.println("修改-imgUrl="+imgUrl);
        System.out.println("修改-profile="+profile);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", companyName);
        map.put("imgUrl", imgUrl);
        map.put("profile", profile);
        String str=companyService.updateCompanyOneDetails(map);
        System.out.println("修改-str="+str);
        if(str!=null){
            System.out.println("修改str!=null=》"+str);
            return " ";
        }
        return "/admin/compListEdit/"+id+"/10/edit";
    }


    /**
     * 企业列表-修改密码-页面（显示数据）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListPwd/{id}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyPwdPage(@PathVariable("id") Long id,Model model){
        System.out.println("修改密码-显示-id="+id);
        String companyOneDetails=companyService.getCompanyOneDetails(id);
        Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
        System.out.println("editmap-name="+editmap.get("name"));
        System.out.println("editmap-password="+editmap.get("password"));
        model.addAttribute("companyPwd",editmap);
        return "company/companyListPwd";
    }

    /**
     * 企业列表-修改密码-数据处理
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "/admin/compListPwdData/update/edit",method = RequestMethod.POST)
    public String getCompanyPwdData(@RequestParam("id") Long id,@RequestParam("password") String password){
        System.out.println("修改密码-保存-id="+id);
        System.out.println("修改密码-保存-password="+password);
        String updateCompanyPwd=null;
        if(id!=0){
            updateCompanyPwd=companyService.updateCompanyOnePwd(id,password);
        }
        if (updateCompanyPwd!=null){
            System.out.println("updateCompanyPwd="+updateCompanyPwd);
        }
        return " ";
    }

    /**
     * 企业列表-删除-数据处理
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/compListStatusData/update/edit.ajax",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Boolean> getCompanyStatusData(@RequestParam("id") Long id){
        System.out.println("修改密码-保存-id="+id);
        HashMap<String,Boolean> delBooleanMap=new  HashMap<String,Boolean>();
        //删除企业
        int status=0;
        String updateCompanyStatus=null;
        if(id!=0){
            updateCompanyStatus=companyService.updateCompanyOneStatus(id,status);
        }
        if (updateCompanyStatus!=null){
            delBooleanMap.put("true",true);
        }
        delBooleanMap.put("false",false);
        return delBooleanMap;
    }

    /**
     * 企业添加-添加信息-页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/save/page/edit", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public String getAddPage(Model model) {
        return "company/companyListSave";
    }
}
