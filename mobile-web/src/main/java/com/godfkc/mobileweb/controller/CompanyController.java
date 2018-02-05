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

}
