package com.godfkc.center.service;

import com.godfkc.center.entity.User;

/**
 * @author wj
 * @date 10:07 2018/1/31
 * @description 用户相关
 */
public interface UserService {
    User findByPhoneAndPassword(String  phone,String password);
}
