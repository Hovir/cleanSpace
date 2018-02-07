package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.CardService;
import com.godfkc.mobileweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wj
 * @date 9:18 2018/1/31
 * @description 页面跳转
 */
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;


    @Value("${session.key.userPhone}")
    private String sessionKeyUserPhone;
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
     * 环保咨询详情页面
     */
    @RequestMapping("/newsDetails")
    public String toNewsDetails(Long newId,Model model) {
        model.addAttribute("newId",newId);
        return "news-detal";
    }

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
     * 银行卡信息
     */
    @RequestMapping("/bankCardPage")
    public String toBankcard(){
        return "bankcard";
    }


    /**
     * 预约检测信息填写
     */
    @RequestMapping("/subscribe1")
    public String toSubscribe1(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            Long id=userService.selectUserIdByPhone(phone);
            boolean flag=cardService.selectUserCard(id);
            if(flag){
                return "subscribe1";
            }else {
                return "bespeak-login";
            }
        }else {
            return "login";
        }
    }


    /**
     * 预约治理信息填写
     */
    @RequestMapping("/subscribe")
    public String toSubscribe(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            Long id=userService.selectUserIdByPhone(phone);
            boolean flag=cardService.selectUserCard(id);
            if(flag){
                return "subscribe";
            }else {
                return "bespeak-login";
            }
        }else {
            return "login";
        }
    }

    /**
     * 我的预约页面
     */
    @RequestMapping("/toMyReservation")
    public String myReservation(){
        return "my-reservation";
    }

    /**
     * 预约检测填写页面跳转
     */
    /*@RequestMapping("/subscribePage")
    public String subscribePage(){
        return "subscribe";
    }*/

    /**
     * 个人中心跳转
     * @return
     */
    @RequestMapping("/centerPage")
    public String personCenterPage(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            return "center";
        }else {
           return "login";
        }
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
        return "detection_and_management";
    }


    /**
     * 修改个人信息跳转
     * @return
     */
    @RequestMapping("/personCenter")
    public String toPersonCenter(HttpServletRequest request){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            return "personCenter";
        }else {
            return "login";
        }
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
    @RequestMapping("/upload/{orderId}")
    public String toUpload(Model model, @PathVariable Long orderId) {
        model.addAttribute("orderId", orderId);
        return "upload";
    }

    /**
     * 绑定银行卡
     * @return
     */
    @RequestMapping("/bindCardPage")
    public String bindCardPage(){
        return "bindCard";
    }
    /**
     * 密码管理跳转
     * @return
     */
    @RequestMapping("/changePwd")
    public String toChangePwd(){
        return "changePwd";
    }


    /**
     * 激活卡付款页面
     * @return
     */
    @RequestMapping("/pay")
    public String toPay(HttpServletRequest request, Model model,Long cardId){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            model.addAttribute("cardId",cardId);
            return "pay";
        }else {
            return "login";
        }
    }


    /**
     * 提现
     */
    @RequestMapping("/moneyPage")
    public String moneyPage(){
        return "money";
    }

    /**筛选
     *
     */
    @RequestMapping("/filter")
    public String screenPage(){return "screen";}
}
