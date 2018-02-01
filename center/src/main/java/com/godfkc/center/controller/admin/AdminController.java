package com.godfkc.center.controller.admin;

import com.godfkc.center.entity.Admin;
import com.godfkc.center.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping(value="/admin/doLogin/{name}/{password}",method = RequestMethod.POST)
    public Admin adminDoLogin(@PathVariable("name") String name, @PathVariable("password") String password){
        System.out.println("POST:name="+name);
        Admin admin=adminService.getAdminLogin(name,password,1);
        return admin;
    }
}
