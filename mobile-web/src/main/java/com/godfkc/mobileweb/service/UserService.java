package com.godfkc.mobileweb.service;



/**
 * @author wj
 * @date 10:18 2018/1/31
 * @description
 */
public interface UserService {
    String findByPhoneAndPassword(String phone, String password);

    String doLogin(String phone, String password);

    Long selectUserIdByPhone(String phone);
}
