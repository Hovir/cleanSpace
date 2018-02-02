package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Order;

import java.util.List;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 19:40 2018/2/1
 **/
public interface OrderService {

    List<Order> findByCompanyIdOrUserId(String companyId,String userId);

    Order findOrderById(Long id);

}
