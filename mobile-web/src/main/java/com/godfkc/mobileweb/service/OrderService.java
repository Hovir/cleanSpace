package com.godfkc.mobileweb.service;


import com.godfkc.common.pojo.mobile.OrderVo;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 18:44 2018/2/1
 **/
public interface OrderService {

    String findByCompanyIdOrUserId(Long companyId, Long userId);

    boolean addOrder(OrderVo orderVo);

    String findOrderById(String id);

    boolean updateReport(Long orderId, Long companyId, String url);

    boolean updateReportAndRemark(Long id, String remark, String report);

    String findAllOrderByCompanyId(Long id);
}
