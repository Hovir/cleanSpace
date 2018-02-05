package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Order;
import com.godfkc.center.entity.User;
import com.godfkc.center.service.mobile.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Integer companyId = (Integer)map.get("companyId");
        Integer userId = (Integer) map.get("userId");
        List<Order> orderList = orderService.findByCompanyIdOrUserId(companyId, userId);
        return orderList;
    }

    @RequestMapping("/addOrder/{name}/{phone}/{state}/{city}/{district}/{address}/{appointmentTime}/{remark}/{userId}/{status}")
    public boolean addOrder(@PathVariable("name") String name,@PathVariable("phone")String phone,@PathVariable("state")String state,@PathVariable("city")String city,@PathVariable("district")String district, @PathVariable("address")String address,@PathVariable("appointmentTime")String appointmentTime,@PathVariable("remark")String remark,@PathVariable("userId")Long userId,@PathVariable("status")Integer status) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(appointmentTime);
        Order order=new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setState(state);
        order.setCity(city);
        order.setDistrict(district);
        order.setAddress(address);
        order.setAppointmentTime(date);
        order.setRemark(remark);
        User user=new User();
        user.setId(userId);
        order.setUser(user);
        order.setStatus(status);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        Order order1=orderService.addOrder(order);
        return order1 == null?false:true;
    }

    @RequestMapping("/findOrderById")
    public Order findOrderById(@RequestBody String id){
        Long logId = Long.parseLong(id);
        return orderService.findOrderById(logId);
    }
}
