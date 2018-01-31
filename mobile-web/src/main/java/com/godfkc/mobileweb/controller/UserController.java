package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> findUserByPhoneAndPassword(String phone,String password){
        Map<String,Object> map = new HashMap<>(16);
        String user = userService.findByPhoneAndPassword(phone,password);
        if(user==null){
            map.put("flag",0);
            map.put("info","用户名或密码错误");
        }else{
            map.put("flag",1);
            map.put("user",user);
        }
        return map;
    }
}
