package com.godfkc.mobileweb.service;


import com.godfkc.common.pojo.dataTables.Order;
import com.godfkc.common.pojo.mobile.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 18:44 2018/2/1
 **/
public interface OrderService {

    String findByCompanyIdOrUserId(Long userId);

    boolean addOrder(OrderVo orderVo);

    String findOrderById(String id);

    boolean updateReport(Long orderId, Long companyId, String url);

    boolean updateReportAndRemark(Long id, Long companyId,String remark, String report);

    String findAllOrderByCompanyId(Long companyId);

    String findOrderByCondition(Map<String, Object> param_map);
}
