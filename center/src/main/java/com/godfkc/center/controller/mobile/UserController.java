package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Order;
import com.godfkc.center.entity.User;
import com.godfkc.center.service.mobile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author syx
 * @date 13:50 2018-2-1
 * @description
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //用户登陆验证
    @RequestMapping("/doLogin/{phone}/{password}")
    public User doLogin(@PathVariable("phone")String phone, @PathVariable("password")String password){
        User user = userService.doLogin(phone,password);
        return user;
    }

    //根据手机号查询useId
    @RequestMapping("/selectUserIdByPhone/{phone}")
    public Long selectUserIdByPhone(@PathVariable("phone")String phone){
        Long id=userService.selectUserIdByPhone(phone);
        return id;
    }

    //插入用户信息
    @RequestMapping("/saveUserInfo")
    public boolean saveUserInfo(@RequestBody User user){
        User user1 = userService.saveUserInfo(user);
        if(user1 == null){
            return false;
        }
        return true;
    }

    //修改密码
    @RequestMapping("/updatePwdByPhone")
    public boolean updatePwdByPhone(@RequestBody Map map){
        String phone = (String) map.get("phone");
        String password = (String) map.get("password");
        int i = userService.updatePwdByPhone(phone, password);
        if (i==1){
            return true;
        }
        return false;
    }
}
