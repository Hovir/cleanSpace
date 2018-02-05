package com.godfkc.managerweb.controller;

import com.godfkc.managerweb.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${session.key.adminName}")
    private String sessionKeyAdminName;

    @Value("${session.key.adminPwd}")
    private String sessionKeyPwd;


    @RequestMapping("/admin/login")
    public String adminLogin(HttpServletRequest request){
        return "admin/adminLgoin";
    }


    /**
     * 用户登录
     */
    @RequestMapping(value="/admin/doLogin",method= RequestMethod.POST)
    public String adminDoLogin(String name, String password, HttpServletRequest request){
        //System.out.println("name="+name);
        password= DigestUtils.md5Hex(password);
        //System.out.println("password="+password);
        Boolean adminBoolean=adminService.getAdminLogin(name,password);
        if(adminBoolean){
            System.out.println("adminBoolean="+adminBoolean);
            HttpSession httpSession=request.getSession();
            httpSession.setAttribute(sessionKeyAdminName,name);
            httpSession.setAttribute(sessionKeyPwd,password);
            return "redirect:/admin/index";
        }
        //System.out.println("adminBoolean="+adminBoolean);
        return "admin/adminLgoin";
    }

    /*首页*/
    @RequestMapping(value = "/admin/index")
    public String adminIndex(HttpServletRequest request, Model model) {
        String name=(String)request.getSession().getAttribute(sessionKeyAdminName);
        if(name==null){
            return "admin/adminLgoin";
        }
        model.addAttribute("name",name);
        return "/manager/_index";
    }

}
