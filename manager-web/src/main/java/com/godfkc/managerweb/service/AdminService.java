package com.godfkc.managerweb.service;

public interface AdminService {

    //用户登录-查询用户是否存在
    Boolean getAdminLogin(String name, String password);
}
