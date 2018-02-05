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
     * 下级代理页面
     */
    @RequestMapping("/more")
    public String toMore(){
        return "more";
    }

    /**
     * 添加银行卡
     */
    @RequestMapping("/addCard")
    public String toAddCard(){
        return "addCard";
    }


    /**
     * 银行卡信息
     */
    @RequestMapping("/bankcard")
    public String toBankcard(){
        return "bankcard";
    }


    /**
     * 预约信息填写
     */
    @RequestMapping("/subscribe")
    public String toSubscribe(){
        return "subscribe";
    }

    /**
     * 我的预约页面
     */
    @RequestMapping("/toMyReservation")
    public String myReservation(){
        return "my-reservation";
    }

    /**
     * 企业中心
     */
    @RequestMapping("/enterprisePage")
    public String enterprisePage(){
        return "enterprise";
    }

    /**
     * 预约检测填写页面跳转
     */
    /*@RequestMapping("/subscribePage")
    public String subscribePage(){
        return "subscribe";
    }*/

    /**
     * 银行卡页面
     */
    /*@RequestMapping("/bankcardPage")
    public String bankcardPage(){
        return "bankcard";
    }*/

    /**
     * 个人中心跳转
     * @return
     */
    @RequestMapping("/centerPage")
    public String personCenterPage(){
        return "center";
    }

    /**
     * 明细页跳转
     * @return
     */
    @RequestMapping("/detailPage")
    public String detailPage(){
        return "detail";
    }

    /**
     * 检测与管理页
     * @return
     */
    @RequestMapping("/detectionManagementPage")
    public String detectionManagementPage(){
        return "detection and management";
    }


    /**
     * 修改个人信息跳转
     * @return
     */
    @RequestMapping("/personCenter")
    public String toPersonCenter(){
        return "personCenter";
    }


    /**
     * 修改个人信息跳转
     * @return
     */
    @RequestMapping("/screen")
    public String toScreen(){
        return "screen";
    }

    /**
     * 上传检测报告
     */
    @RequestMapping("/upload")
    public String toUpload(){return "upload";}


}
