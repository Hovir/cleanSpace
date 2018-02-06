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

    //测试
    List<Order> findOrderByStatus(Integer status);
}
