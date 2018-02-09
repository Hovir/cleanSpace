package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 订单信息
 * lqj add 2018-2-1
 */
public interface ManagerDispatchOrderService {

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    String findAllOrderByType(SentParameters sentParameters,Integer type);

    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    boolean updateOrderReport(String report,Long id);

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    String findOrderByTypeAndStatus(SentParameters sentParameters,Integer type,Integer status);

    //添加检测公司 zhaozhb
    void saveCompany(Long ztreeId, Long id);

    //上传报告 zhaozhb
    void updateReprot(Map<String,Object> map);

    /*//通过company_id查询funds表中是否已经存有该公司信息 lqj add 2018-2-8
    String findFundsByCompanyId(Integer CompanyId);

    //插入funds表 lqj add 2018-2-8
    Integer saveFunds(Date createTime,Long money,String payPassword,Integer status,Date update_time,Long companyId);

    //通过companyId更改funds表中money字段 lqj add 2018-2-8
    Integer updateFundsMoneyByCompanyId(Long companyId);

    //通过Id查询company表 lqj add 2018-2-8
    String findCompanyById(Long id);*/


}
