package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

import java.util.Map;
import java.util.Objects;

/**
 * 订单信息
 * lqj add 2018-2-1
 */
public interface ManagerDispatchOrderService {

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    String findAllOrderByType(SentParameters sentParameters,Integer type);

}
