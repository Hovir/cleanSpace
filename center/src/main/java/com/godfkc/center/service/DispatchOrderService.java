package com.godfkc.center.service;

import com.godfkc.center.entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 派遣订单
 * lqj add 2018-2-1
 */

public interface DispatchOrderService {

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    Page<Order> findAllOrderByType(Integer type, Integer page, Integer size, String dir, String data, String search);

    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    boolean updateOrderReport(String report,Long id);

    //测试
    List<Order> findOrderByStatus(Integer status);

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    Page<Order> findAllOrderByTypeAndStatus(Integer type,Integer status, Integer page, Integer size, String dir, String data, String search);

    void saveCompany(Long ztreeId, Long id);

    void updateReprot(Order order);
}
