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
    public String toHomePage() {
        return "index";
    }

    /**
     * 跳转登录页面
     */
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }


    /**
     * 跳转注册页面
     */
    @RequestMapping("/goToRegister")
    public String goToRegister(){
        return "register";
    }

    /**
     * 忘记密码页面
     */
    @RequestMapping("/goToForgetPwd")
    public String goToForgetPwd(){
        return "forgetpwd";
    }


    /**
     * 环保资讯页面
     */
    @RequestMapping("/news")
    public String toNews() {
        return "news";
    }

    /**
     * 环保咨询页面
     */

    /**
     * 预约检测页面
     */
    @RequestMapping("/bespeakLogin")
    public String toBespeakLogin(){
        return "bespeak-login";
    }

    /**
     *环保商家页面
     */
    @RequestMapping("/bussiness")
    public String bussiness(){
        return "bussiness";
    }

    /**
     * 企业登陆页面
     */
    @RequestMapping("/companyLogin")
    public String toComLogin(){
        return "com-login";
    }

    /**
     * 更多页面
     */
    @RequestMapping("/more")
    public String toMore(){
        return "more";
    }
}
