package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.ManagerDispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

}
