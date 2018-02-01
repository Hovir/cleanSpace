package com.godfkc.center.service.admin;


import com.godfkc.center.entity.Admin;

public interface AdminService {
    //查询用户存在的信息
    Admin getAdminLogin(String name, String password);

    //查询用户存在的信息
    Admin getAdminLogin(String name, String password,int status);
}
