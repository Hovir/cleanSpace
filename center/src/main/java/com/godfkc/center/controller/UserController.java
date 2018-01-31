package com.godfkc.center.controller;

import com.godfkc.center.entity.User;
import com.godfkc.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wj
 * @date 10:13 2018/1/31
 * @description 用户相关
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login/{phone}/{password}")
    public User findUserByPhoneAndPassword(@PathVariable("phone")String phone,@PathVariable("password")String password){
        System.out.println("=============================================");
        System.out.println("================================="+phone+"=============="+password);
        return userService.findByPhoneAndPassword(phone,password);
    }
}
