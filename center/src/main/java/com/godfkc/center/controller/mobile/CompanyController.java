package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Company;
import com.godfkc.center.service.mobile.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
@RestController
public class CompanyController{
    @Autowired
    private CompanyService companyService;
    @RequestMapping(value = "/company/doLogin/{name}/{password}",method = RequestMethod.POST)
    public Company login(@PathVariable(value = "name")String name, @PathVariable(value = "password")String password){
        Company company = companyService.findByNameAndPassword(name, password);
        return company;
    }

    @RequestMapping("/company/test")
    public String test(){
        System.out.println("come in");
        return "b";
    }
}
