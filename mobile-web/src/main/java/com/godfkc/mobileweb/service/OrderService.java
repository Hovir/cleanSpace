package com.godfkc.mobileweb.service;


import com.godfkc.common.pojo.mobile.OrderVo;

import java.util.List;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 18:44 2018/2/1
 **/
public interface OrderService {

    String findByCompanyIdOrUserId(Long companyId, Long userId);

    boolean addOrder(OrderVo orderVo);

    String findOrderById(String id);
}
