package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Order;
import com.godfkc.center.service.mobile.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 19:44 2018/2/1
 **/
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findOrderList")
    public List<Order> findOrderList(@RequestBody Map map){
        String companyId = (String)map.get("companyId");
        String userId = (String)map.get("userId");
        List<Order> orderList = orderService.findByCompanyIdOrUserId(companyId, userId);
        return orderList;
    }
}
