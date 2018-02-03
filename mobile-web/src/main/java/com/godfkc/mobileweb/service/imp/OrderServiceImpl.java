package com.godfkc.mobileweb.service.imp;

import com.godfkc.common.pojo.mobile.OrderVo;
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
    public String findByCompanyIdOrUserId(Long companyId, Long userId) {
        String url = centerUrl + "findOrderList";
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",companyId);
        map.put("userId",userId);
        String s = this.restTemplate.postForObject(url, map, String.class);
        return s;
    }

    @Override
    public boolean addOrder(OrderVo orderVo) {
        String url = centerUrl + "addOrder/{name}/{phone}/{state}/{city}/{district}/{address}/{appointmentTime}/{remark}/{userId}/{status}";
        Map<String,Object> map = new HashMap<>();
        map.put("name",orderVo.getName());
        map.put("phone",orderVo.getPhone());
        map.put("state",orderVo.getState());
        map.put("city",orderVo.getCity());
        map.put("district",orderVo.getDistrict());
        map.put("address",orderVo.getAddress());
        map.put("appointmentTime",orderVo.getAppointmentTime());
        map.put("remark",orderVo.getRemark());
        map.put("userId",orderVo.getUserId());
        map.put("status",orderVo.getStatus());
        boolean s = this.restTemplate.getForObject(url,boolean.class, map);
        return s;
    }

    @Override
    public String findOrderById(String id) {
        String url = centerUrl + "findOrderById";
        return this.restTemplate.postForObject(url,id,String.class);
    }

}
