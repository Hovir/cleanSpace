package com.godfkc.mobileweb.controller;

import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Value("${session.key.companyName}")
    private String companyNameSessionKey;
    @Value("${session.key.companyId}")
    private String companyIdSessionKey;
    @Value("${session.key.userName}")
    private String sessionkeyuserName;
    /**
     * 下边栏，点选企业
     *
     * @param:
     * @return:
     * @author: Qi Zining
     * @date: 2018/2/2
     */
    @RequestMapping("/myCompany")
    public ModelAndView turnToMyCompany(HttpServletRequest request) {
        String companyName = (String) request.getSession().getAttribute(companyNameSessionKey);
        System.out.println("/myCompany: name in session " + companyName);
        //
        ModelAndView companyPageTurn = null;
        if (null == companyName || companyName.trim().length() <= 0) {
            companyPageTurn= new ModelAndView("com-login");
        } else {//if exist companyName in session, turn to company index page;
            companyPageTurn = new ModelAndView("enterprise");
            String companyDetailByName = companyService.findCompanyDetailByName(companyName);
            System.out.println(companyDetailByName);
            /* set companyId into session*/
            Map<String, Object> stringDetailMap = JsonUtils.JsonToMap(companyDetailByName);
            Integer id = (Integer)stringDetailMap.get("id");
            request.getSession().setAttribute(companyIdSessionKey,id.longValue());

            companyPageTurn.addObject("company", companyDetailByName);
        }
        return companyPageTurn;
    }

    @Value("${session.key.companyId}")
    private String sessionKeyCompanyId;

    @RequestMapping("/loginAjax")
    @ResponseBody
    public boolean checkCompanyName(HttpServletRequest request, String comAccount, String comPwd) {
        boolean flag_login = companyService.findByNameAndPassword(comAccount, comPwd);
        if (flag_login) {
            request.getSession().setAttribute(companyNameSessionKey, comAccount);
        }
        return flag_login;
    }

    @RequestMapping("/showUnderCompany")
    @ResponseBody
    public Map<String,Object> showUnderCompany(HttpServletRequest request){
        Long id = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        Map<String,Object> map=new HashMap<>();
        if(id!=null){
           map.put("flag","1");
           String json=companyService.selectUnderCompany(id);
           map.put("companies",json);
        }else {
          map.put("flag","2");
        }
        return map;
    }

    @RequestMapping("/uploadReport")
    @ResponseBody
    public String uploadReport(MultipartFile report){
        System.out.println("report"+report);
        return "success";
    }

    /**
     * 根据公司id判断是否绑定银行卡
     * @param request
     * @return
     */
    @RequestMapping("/findBandCardByCompanyId")
    @ResponseBody
    public String findByCompanyId(HttpServletRequest request){
        Long id = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if(id != null){
            String byCompanyId = companyService.findByCompanyId(id);
            if (byCompanyId == null){//没有绑定银行卡
                return "2";
            }
            return byCompanyId;
        }
        return "1";
    }

    /**
     * 点击解绑
     */
    @RequestMapping("/unbindMod")
    @ResponseBody
    public String unbindMod(String id){
        if (null != id){
            Long bankCardId = Long.parseLong(id);
            boolean b = companyService.unbindMod(bankCardId);
            if (b){
                return "success";
            }else {
                return "falied";
            }
        }
        return "falied";
    }

    /**
     * 查询所有银行
     */
    @RequestMapping("/findBankDictAll")
    @ResponseBody
    public String findBankDictAll(){
        return companyService.findBankDictAll();
    }


    @RequestMapping("/bindBankCard")
    @ResponseBody
    public String bindBankCard(String cardNo,String bankDictId,String phone,String username,HttpServletRequest request){
        Long compayId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if (null != compayId){
            String byCompanyId = companyService.findByCompanyId(compayId);
            if (byCompanyId != null){
                return "4";
            }
            boolean b = companyService.bindBankCard(cardNo, phone, compayId, bankDictId,username);
            if (b){
               return "2";
            }else {
                return "3";
            }

        }
        return "1";
    }
}
