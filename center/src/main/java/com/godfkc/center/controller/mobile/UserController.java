package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.User;
import com.godfkc.center.service.mobile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author syx
 * @date 13:50 2018-2-1
 * @description
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/doLogin/{phone}/{password}")
    public User doLogin(@PathVariable("phone")String phone, @PathVariable("password")String password){
        User user = userService.doLogin(phone,password);
        return user;
    }
}
