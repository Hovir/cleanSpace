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

    /*企业-列表*/
    @RequestMapping("/admin/compList")
    @ResponseBody
    public String getCompany(SentParameters sentParameters){
        return companyService.getCompaniesList(sentParameters);
    }


    /*企业列表-搜索-条件搜索-页面*/
    @RequestMapping(value = "/admin/compSearch/{dateFrom}/{dateTo}/{companyName}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public String page(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName,Model model) {
        System.out.println("dateFrom1="+dateFrom);
        System.out.println("dateTo1="+dateTo);
        System.out.println("companyName1="+companyName);
        model.addAttribute("dateFrom",dateFrom);
        model.addAttribute("dateTo",dateTo);
        model.addAttribute("companyName",companyName);
        return "company/companyListSearch";
    }

    /*企业列表-搜索-条件搜索-数据*/
    @RequestMapping("/admin/compSearchData/{dateFrom}/{dateTo}/{companyName}")
    @ResponseBody
    public String getCompanySearchData(SentParameters sentParameters, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo, @PathVariable("companyName") String companyName){
        System.out.println("dateFrom="+dateFrom);
        System.out.println("dateTo="+dateTo);
        System.out.println("companyName="+companyName);
        return companyService.getCompaniesSearch(sentParameters,dateFrom,dateTo,companyName);
    }
    /*企业列表-（1）详情-页面-数据+显示*/
    @RequestMapping(value = "/admin/compListShow/{id}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyShow(@PathVariable("id") Long id,Model model){
        System.out.println("编辑id="+id);
        String companyOneDetails=companyService.getCompanyOneDetails(id);
        Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
        System.out.println("editmap-name="+editmap.get("name"));
        System.out.println("editmap-imgUrl="+editmap.get("imgUrl"));
        System.out.println("editmap-profile="+editmap.get("profile"));
        model.addAttribute("companyShow",editmap);
        return "company/companyListShow";
    }

    /*企业列表-（1）编辑-页面-数据+显示*/
    @RequestMapping(value = "/admin/compListEdit/{id}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyEdit(@PathVariable("id") Long id,Model model){
        System.out.println("编辑id="+id);
        String companyOneDetails=companyService.getCompanyOneDetails(id);
        Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
        System.out.println("editmap-name="+editmap.get("name"));
        System.out.println("editmap-imgUrl="+editmap.get("imgUrl"));
        System.out.println("editmap-profile="+editmap.get("profile"));
        model.addAttribute("companyEdit",editmap);
        return "company/companyListEdit";
    }

    /*企业列表-（1）编辑-页面-数据-保存*/
    @RequestMapping("/admin/compListEditData/update/edit")
    public String getCompanyEditData(@RequestParam("id") Long id,@RequestParam("companyName") String companyName,@RequestParam("companyUrl") String companyUrl,@RequestParam("companyProfile") String companyProfile){
        System.out.println("====修改数据========");
        System.out.println("id="+id);
        System.out.println("companyName="+companyName);
        System.out.println("companyUrl="+companyUrl);
        System.out.println("companyProfile="+companyProfile);
        if(id!=0){
            //查询元数据
            String companyOneDetails=companyService.getCompanyOneDetails(id);
            Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
            String beforeImgUrl=(String)editmap.get("imgUrl");
            //判断是否上传新头像图片
            if(companyUrl!=null&&companyUrl!="") {
                BASE64Decoder decoder = new BASE64Decoder();
                String[] arr = companyUrl.split(",");
                //取得文件类型
                String imgType = arr[0].substring(arr[0].indexOf("/")+1,arr[0].indexOf(";"));
                //取得密文
                companyUrl = arr[1];
                //Base64解码
                byte[] b = new byte[0];
                try {
                    b = decoder.decodeBuffer(companyUrl);
                    for (int i = 0; i < b.length; ++i) {
                        if (b[i] < 0) {
                            b[i] += 256;
                        }
                    }
                    //上传图片
                    FastDFSClient fastDFSClient=new FastDFSClient("fastDFS.properties");
                    String imagePath = fastDFSClient.uploadFile(b, imgType);
                    //更改数据库图片
                    companyUrl= PictureServerConstants._PICTURE_SERVER_URL+imagePath;
                    //删除原有图片
                    if(beforeImgUrl!=null) {
                        Integer rows = fastDFSClient.deleteFile(beforeImgUrl.substring(20));
                        if(rows==0) {
                            System.out.println("========删除成功========");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                companyUrl=beforeImgUrl;
            }
            //保存数据
            String companyUpdate=companyService.updateCompanyOneDetails(id,companyName,companyUrl,companyProfile);
            //返回all页面
            if(("1").equals(companyUpdate)){
                System.out.println("===修改数据成功====");
            }
        }
        return "";
    }

    /*企业列表-（1）修改密码-页面-数据+显示*/
    @RequestMapping(value = "/admin/compListPwd/{id}/edit",method = {RequestMethod.GET,RequestMethod.POST},produces = "text/html;charset=UTF-8")
    public String getCompanyPwd(@PathVariable("id") Long id,Model model){
        System.out.println("修改密码-显示-id="+id);
        String companyOneDetails=companyService.getCompanyOneDetails(id);
        Map<String,Object> editmap= JsonUtils.JsonToMap(companyOneDetails);
        System.out.println("editmap-name="+editmap.get("name"));
        System.out.println("editmap-password="+editmap.get("password"));
        model.addAttribute("companyPwd",editmap);
        return "company/companyListPwd";
    }

    /*企业列表-（1）修改密码-页面-数据处理*/
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

    /*企业列表-（1）删除-页面-数据处理*/
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
}
