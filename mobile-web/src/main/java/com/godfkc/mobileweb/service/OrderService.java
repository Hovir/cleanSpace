package com.godfkc.mobileweb.service;


import java.util.List;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 18:44 2018/2/1
 **/
public interface OrderService {
    String findByCompanyIdOrUserId(String companyId, String userId);
}
