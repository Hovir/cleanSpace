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
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String findByCompanyIdOrUserId(Long companyId, Long userId) {
        String url = centerUrl + "findOrderList";
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        String s = this.restTemplate.postForObject(url, map, String.class);
        return s;
    }

    @Override
    public boolean addOrder(OrderVo orderVo) {
        String url = centerUrl + "addOrder/{name}/{phone}/{state}/{city}/{district}/{address}/{appointmentTime}/{remark}/{userId}/{type}";
        Map<String, Object> map = new HashMap<>();
        map.put("name", orderVo.getName());
        map.put("phone", orderVo.getPhone());
        map.put("state", orderVo.getState());
        map.put("city", orderVo.getCity());
        map.put("district", orderVo.getDistrict());
        map.put("address", orderVo.getAddress());
        map.put("appointmentTime", orderVo.getAppointmentTime());
        map.put("remark", orderVo.getRemark());
        map.put("userId", orderVo.getUserId());
        map.put("status", orderVo.getStatus());
        map.put("type", orderVo.getType());
        boolean s = this.restTemplate.getForObject(url, boolean.class, map);
        return s;
    }

    @Override
    public String findOrderById(String id) {
        String url = centerUrl + "findOrderById";
        return this.restTemplate.postForObject(url, id, String.class);
    }

    @Override
    public boolean updateReport(Long orderId, Long companyId, String report) {

        String url = centerUrl + "updateReportById";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", orderId);
        paramMap.put("report", report);
        paramMap.put("companyId", companyId);
        return this.restTemplate.postForObject(url, paramMap, boolean.class);
    }

    @Override
    public boolean updateReportAndRemark(Long id,Long companyId, String remark, String report) {
        String url = centerUrl + "updateReportAndRemark";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("companyId",companyId);
        paramMap.put("report", report);
        paramMap.put("remark", remark);
        return this.restTemplate.postForObject(url, paramMap, boolean.class);
    }

    @Override
    public String findOrderByCondition(Map<String, Object> param_map) {
        String url = centerUrl + "findOrderByCondition";
        String returnJson = this.restTemplate.postForObject(url, param_map, String.class);
        return returnJson;
    }

    @Override
    public String findAllOrderByCompanyId(Long companyId) {
        String url = centerUrl + "findAllOrderByCompanyId/{companyId}";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("companyId", companyId);
        String returnJson = this.restTemplate.getForObject(url, String.class, paramMap);
        return returnJson;
    }
}
