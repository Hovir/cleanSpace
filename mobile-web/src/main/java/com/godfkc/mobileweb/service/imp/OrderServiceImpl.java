package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 19:54 2018/2/1
 **/
@Service
@Transactional
@PropertySource("classpath:config.properties")
public class OrderServiceImpl implements OrderService{
    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String findByCompanyIdOrUserId(String companyId, String userId) {
        String url = centerUrl + "findOrderList";
        Map<String,String> map = new HashMap<>();
        map.put("companyId",companyId);
        map.put("userId",userId);
        String s = this.restTemplate.postForObject(url, map, String.class);
        return s;
    }

    @Override
    public String findOrderById(String id) {
        String url = centerUrl + "findOrderById";
        return this.restTemplate.postForObject(url,id,String.class);
    }

}
