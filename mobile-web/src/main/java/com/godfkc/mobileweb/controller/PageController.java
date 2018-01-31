package com.godfkc.mobileweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wj
 * @date 9:18 2018/1/31
 * @description 页面跳转
 */
@Controller
public class PageController {
    /**
     * 首页
     */
    @RequestMapping("/index")
    public String toHomePage(){
        return "index";
    }

    /**
     * 跳转登录页面
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
}
