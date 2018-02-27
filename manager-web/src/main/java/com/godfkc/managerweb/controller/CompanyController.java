package com.godfkc.managerweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.managerweb.service.AddressDictService;
import com.godfkc.managerweb.service.CompanyService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private AddressDictService addressDictService;

    /**
     * 企业-列表-数据
     *
     * @param sentParameters
     * @return
     */
    @RequestMapping("/admin/compList")
    @ResponseBody
    public String getCompany(SentParameters sentParameters) {
        return companyService.getCompaniesList(sentParameters);
    }


    /**
     * 企业列表-条件搜索-页面
     *
     * @param dateFrom
     * @param dateTo
     * @param companyName
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compSearch/{dateFrom}/{dateTo}/{companyName}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public String getSearchPage(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName, Model model) {
        //System.out.println("dateTo1=" + dateTo);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        model.addAttribute("companyName", companyName);
        return "company/companyListSearch";
    }

    /**
     * 企业列表-条件搜索-数据
     *
     * @param sentParameters
     * @param dateFrom
     * @param dateTo
     * @param companyName
     * @return
     */
    @RequestMapping("/admin/compSearchData/{dateFrom}/{dateTo}/{companyName}")
    @ResponseBody
    public String getCompanySearchData(SentParameters sentParameters, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName) {
        //System.out.println("dateTo2=" + dateTo);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate= simpleDateFormat.format(date);
        if (dateTo!=null&&nowDate.equals(dateTo)&&dateTo.length()>8) {
            //2018-02-11
            String dateToStrOne=dateTo.substring(0,8);
            String dateToStrTwo=dateTo.substring(8);;
            if(dateToStrTwo!= null && dateToStrTwo.length()>0){
                int dateToInt=Integer.parseInt(dateToStrTwo);
                dateToInt++;
                dateToStrTwo=String.valueOf(dateToInt);
            }
            dateTo=dateToStrOne+dateToStrTwo;
            //System.out.println("新 dateTo="+dateTo);
        }
        return companyService.getCompaniesSearch(sentParameters, dateFrom, dateTo, companyName);
    }

    /**
     * 企业添加-添加信息-页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/save/page/edit", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public String getAddPage(Model model) {
        return "company/companyListSave";
    }

    /**
     * 企业列表-添加信息-页面-公司名称
     *
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/add/companyName.ajax")
    @ResponseBody
    public String getCompanyName() {
        String companyNameList = companyService.getCompanyNameList();
        //return "company/companyListAdd";
        return companyNameList;
    }

    /**
     * 企业列表-添加信息-页面-公司级别
     *
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/add/{companyId}/levelName.ajax")
    @ResponseBody
    public String getLevelName(@PathVariable("companyId") Long companyId) {
        String serviceLevelNameLists = null;
        if (companyId == 0) {
            return serviceLevelNameLists;
        } else if (companyId == 1) {
            serviceLevelNameLists = companyService.getLevelNameList();
        } else {
            companyService.getLevelsByCompanyIdAndLevelId(companyId);
            serviceLevelNameLists = companyService.getLevelsByCompanyIdAndLevelId(companyId);
        }
        //return "company/companyListAdd";
        return serviceLevelNameLists;
    }

    /**
     * 企业列表-添加信息-跳转-页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListAdd/add/{num}/edit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String getCompanyAddPage(@PathVariable("num") String num, Model model) {
        if (("failer").equals(num)) {
            model.addAttribute("failer", "保存并提交失败！");
        }
        if (("sucess").equals(num)) {
            model.addAttribute("failer", "保存并提交成功！");
        }
        return "company/companyListAdd";
    }

    /**
     * 企业列表-添加信息-数据-提交
     *
     * @param companyName
     * @param imgUrl
     * @param levelId
     * @param profile
     * @return
     */
    @RequestMapping(value = "/admin/compListAddData/add/edit", method = RequestMethod.POST)
    public String getCompanyAddData(@RequestParam("companyName") String companyName, @RequestParam("imgUrl") String imgUrl, @RequestParam("levelId") Long levelId, @RequestParam("profile") String profile, @RequestParam("bn") String bn, @RequestParam("password") String password, @RequestParam("parentId") Long parentId,
                                    @RequestParam("state") String state,@RequestParam("city") String city,@RequestParam("district") String district) {
        String str =null;
        if(state!=null&&city!=null&&district!=null){
            Map<String, Object> mapCompany = new HashMap<>();
            mapCompany.put("name", companyName);
            mapCompany.put("imgUrl", imgUrl);
            mapCompany.put("profile", profile);
            mapCompany.put("bn", bn);
            password= DigestUtils.md5Hex(password);
            //System.out.println("password1"+password);
            mapCompany.put("password", password);
            str = companyService.insertCompanyALL(mapCompany,levelId, parentId,state,city,district);
        }
        if (str != null) {
            //成功-""
            return "/admin/compListAdd/add/sucess/edit";
        }
        //失败-""
        return "/admin/compListAdd/add/failer/edit";
    }


    /**
     * 企业列表-详情-页面（显示数据）
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListShow/{id}/edit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String getCompanyShowPage(@PathVariable("id") Long id, Model model) {
        String companyOneDetails = companyService.getCompanyOneDetails(id);
        Map<String, Object> editmap = JsonUtils.JsonToMap(companyOneDetails);
        model.addAttribute("companyShow", editmap);
        return "company/companyListShow";
    }

    /**
     * 企业列表-详情-页面（显示数据）（时间传值）
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListShow/{id}/{date}/edit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String getCompanyShowPage2(@PathVariable("id") Long id, @PathVariable("date") String date, Model model) {
        String companyOneDetails = companyService.getCompanyOneDetails(id);
        Map<String, Object> editmap = JsonUtils.JsonToMap(companyOneDetails);
        editmap.put("createTime", date);
        model.addAttribute("companyShow", editmap);
        return "company/companyListShow";
    }


    /**
     * 企业列表-修改信息-数据处理
     *
     * @param companyName
     * @param imgUrl
     * @param profile
     * @return
     */
    @RequestMapping(value = "/admin/compListEditData/update/edit", method = RequestMethod.POST)
    public String getCompanyEditData(@RequestParam("id") Long id, @RequestParam("companyName") String companyName, @RequestParam("imgUrl") String imgUrl, @RequestParam("profile") String profile) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", companyName);
        map.put("imgUrl", imgUrl);
        map.put("profile", profile);
        String str = companyService.updateCompanyOneDetails(map);
        if (str != null) {
            return " ";
        }
        return "/admin/compListEdit/" + id + "/10/edit";
    }

    /**
     * 企业列表-编辑-页面（显示数据）
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListEdit/{id}/{num}/edit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String getCompanyEditPage(@PathVariable("id") Long id, @PathVariable("num") Long num, Model model) {
        String companyOneDetails = companyService.getCompanyOneDetails(id);
        Map<String, Object> editmap = JsonUtils.JsonToMap(companyOneDetails);
        model.addAttribute("companyEdit", editmap);
        if (num == 10) {
            model.addAttribute("fail", "保存并提交失败！");
        }
        return "company/companyListEdit";
    }

    /**
     * 企业列表-修改密码-页面（显示数据）
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListPwd/{id}/edit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String getCompanyPwdPage(@PathVariable("id") Long id, Model model) {
        String companyOneDetails = companyService.getCompanyOneDetails(id);
        Map<String, Object> editmap = JsonUtils.JsonToMap(companyOneDetails);
        model.addAttribute("companyPwd", editmap);
        return "company/companyListPwd";
    }

    /**
     * 企业列表-修改密码-数据处理
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "/admin/compListPwdData/update/edit", method = RequestMethod.POST)
    public String getCompanyPwdData(@RequestParam("id") Long id, @RequestParam("password") String password) {
        String updateCompanyPwd = null;
        if (id != 0) {
            password=DigestUtils.md5Hex(password);
            //System.out.println("password2"+password);
            updateCompanyPwd = companyService.updateCompanyOnePwd(id, password);
        }
        if (updateCompanyPwd != null) {
            //修改成功
        }
        return " ";
    }

    /**
     * 企业列表-删除-数据处理
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/compListStatusData/update/edit.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Boolean> getCompanyStatusData(@RequestParam("id") Long id) {
        HashMap<String, Boolean> delBooleanMap = new HashMap<String, Boolean>();
        //删除企业
        int status = 0;
        String updateCompanyStatus = null;
        if (id != 0) {
            updateCompanyStatus = companyService.updateCompanyOneStatus(id, status);
        }
        if (updateCompanyStatus != null) {
            delBooleanMap.put("true", true);
        }
        delBooleanMap.put("false", false);
        return delBooleanMap;
    }


    /**
     * 企业列表-公司简介
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/compListProfile/{id}/edit", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    public String getCompanycompListProfilePage(@PathVariable("id") Long id, Model model) {
        String companyOneDetails = companyService.getCompanyOneDetails(id);
        Map<String, Object> editmap = JsonUtils.JsonToMap(companyOneDetails);
        model.addAttribute("companyProfile", editmap);
        return "company/companyListProfile";
    }

    /**
     * 后台列表-list-公司编号验证
     * @param bn
     * @return
     */
    @RequestMapping("/company/compListBn/edit")
    @ResponseBody
    public Map<String, Boolean> getCompaniesBnAndStatus(@RequestParam("bn") String bn){
        //System.out.println("bn="+bn);
        HashMap<String, Boolean> bnBooleanMap = new HashMap<String, Boolean>();
        String companyStr=companyService.getCompaniesByBn(bn);
        if (companyStr!=null){
            bnBooleanMap.put("bnBoolean",true);
        }else {
            bnBooleanMap.put("bnBoolean",false);
        }
        return bnBooleanMap;
    }

    /**
     * 后台列表-list-添加公司信息-级联地址查询
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping("/company/selectAddressDict")
    public String selectAddressDict(Long parentId){
        String json = addressDictService.selectAddressDict(parentId);
        return json;
    }
}
