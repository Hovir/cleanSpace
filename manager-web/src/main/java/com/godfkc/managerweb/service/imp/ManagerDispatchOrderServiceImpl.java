package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.ManagerDispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 订单信息
 * lqj add 2018-2-1
 */

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class ManagerDispatchOrderServiceImpl implements ManagerDispatchOrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    @Override
    public String findAllOrderByType(SentParameters sentParameters, Integer type) {
        String url = centerUrl + "findAllOrderByStatus/{type}";
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("status",status);
        return this.restTemplate.postForObject(url,sentParameters,String.class,type);
    }

    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    @Override
    public boolean updateOrderReport( String report,Long id) {
        String url = centerUrl + "updateOrderReport/{report}/{id}";
        Map<String,Object> map = new HashMap<>();
        map.put("report",report);
        map.put("id",id);
        return this.restTemplate.getForObject(url,boolean.class,map);
    }

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    @Override
    public String findOrderByTypeAndStatus(SentParameters sentParameters, Integer type, Integer status) {
        String url = centerUrl + "findOrderByTypeAndStatus/{type}/{status}";
        return this.restTemplate.postForObject(url,sentParameters,String.class,type,status);
    }

    /**
     * 添加公司 zhaozhb
     * @param ztreeId
     * @param id
     */
    @Override
    public void saveCompany(Long ztreeId, Long id) {
        String url = centerUrl + "saveCompany/{ztreeId}/{id}";
        String json = restTemplate.getForObject(url, String.class, ztreeId, id);
    }

    /**
     * 上传报告 zhaozhb
     *
     */
    @Override
    public void updateReprot(Map<String,Object> map) {
        String url = centerUrl + "dispatch/updateReprot";
        String json = this.restTemplate.postForObject(url,map,String.class);
    }

}
