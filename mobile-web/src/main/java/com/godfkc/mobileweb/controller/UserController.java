package com.godfkc.mobileweb.controller;

import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wj
 * @date 9:40 2018/1/31
 * @description 用户相关
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${session.key.userName}")
    private String sessionKeyUserName;

    @Value("${session.key.userPhone}")
    private String sessionKeyUserPhone;

    @Value("${session.key.userPwd}")
    private String sessionKeyUserPwd;

    @Value("${session.key.userHeadImg}")
    private String sessionKeyUserHeadImg;

    //用户登陆
    @RequestMapping("/doLogin")
    @ResponseBody
    public boolean doLogin(String phone, String password, HttpServletRequest request){
        String makePassword = DigestUtils.md5Hex(password);
        String json = userService.doLogin(phone, makePassword);
        HttpSession httpSession = request.getSession();
        if (json != null&&json.length()>0) {
            Map<String, Object> map = JsonUtils.JsonToMap(json);
            httpSession.setAttribute(sessionKeyUserName,map.get("name"));
            httpSession.setAttribute(sessionKeyUserPhone, phone);
            httpSession.setAttribute(sessionKeyUserPwd, password);
            httpSession.setAttribute(sessionKeyUserHeadImg,map.get("headImg"));
            return true;
        } else {
            return false;
        }
    }
}
