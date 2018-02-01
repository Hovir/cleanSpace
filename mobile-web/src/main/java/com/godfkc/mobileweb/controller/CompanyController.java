package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/loginAjax")
    @ResponseBody
    public boolean checkCompanyName(String comAccount, String comPwd) {
        String byNameAndPassword = companyService.findByNameAndPassword(comAccount, comPwd);
        return (null == byNameAndPassword || byNameAndPassword.trim().length() == 0) ? false : true;
    }
}
