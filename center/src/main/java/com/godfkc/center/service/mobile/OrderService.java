package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 19:40 2018/2/1
 **/
public interface OrderService {

    List<Order> findByCompanyIdOrUserId(Integer userId);

    Order addOrder(Order order);

    Order findOrderById(Long id);

    Order saveOrder(Order order);

    List<Order> findAllOrderByCompanyId(Long companyId);


    List<Order> findAllOrderByCompanyIdAndStatus(Long companyId,Integer status);
}
