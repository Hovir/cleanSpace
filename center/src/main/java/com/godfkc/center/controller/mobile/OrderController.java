package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Order;
import com.godfkc.center.entity.User;
import com.godfkc.center.service.mobile.OrderService;
import com.godfkc.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<Order> findOrderList(@RequestBody Map map) {
        Integer companyId = (Integer) map.get("companyId");
        Integer userId = (Integer) map.get("userId");
        List<Order> orderList = orderService.findByCompanyIdOrUserId(companyId, userId);
        return orderList;
    }

    @RequestMapping("/addOrder/{name}/{phone}/{state}/{city}/{district}/{address}/{appointmentTime}/{remark}/{userId}/{type}")
    public boolean addOrder(@PathVariable("name") String name, @PathVariable("phone") String phone, @PathVariable("state") String state, @PathVariable("city") String city, @PathVariable("district") String district, @PathVariable("address") String address, @PathVariable("appointmentTime") String appointmentTime, @PathVariable("remark") String remark, @PathVariable("userId") Long userId, @PathVariable("type") Integer type) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(appointmentTime);
        Order order = new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setState(state);
        order.setCity(city);
        order.setDistrict(district);
        order.setAddress(address);
        order.setAppointmentTime(date);
        order.setRemark(remark);
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        order.setStatus(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setType(type);
        Order order1 = orderService.addOrder(order);
        return order1 == null ? false : true;
    }

    @RequestMapping("/findOrderById")
    public Order findOrderById(@RequestBody String id) {
        Long logId = Long.parseLong(id);
        return orderService.findOrderById(logId);
    }

    @RequestMapping("/updateReportById")
    public boolean updateReportById(@RequestBody Map<String, Object> map) {
        boolean flag_return = false;
        try {
            Long id = ((Integer) map.get("id")).longValue();
            String report = (String) map.get("report");
            Long companyId = ((Integer) map.get("companyId")).longValue();
            System.out.println("Param map: " + JsonUtils.Object2Json(map));
            Order order = orderService.findOrderById(id);
            if (order.getCompany().getId() == companyId) {
                order.setId(id);
                order.setReport(report);
                order.setStatus(1);
                Order save = orderService.saveOrder(order);
                System.out.println(JsonUtils.Object2Json(order));
                flag_return=(save!=null);
            }
        } catch (Exception e) {
            System.out.println("error!!!!!");
        }finally {
            return flag_return;
        }
    }

    @RequestMapping(value = "/updateReportAndRemark")
    public boolean updateReportAndRemark(@RequestBody Map map){
        try{
            Long id = ((Integer) map.get("id")).longValue();
            String report = (String) map.get("report");
            String remark = (String) map.get("remark");
            Long companyId = ((Integer) map.get("companyId")).longValue();
            Order paramOrder = orderService.findOrderById(id);
            //操作拦截公司id与订单中的company 不匹配
            if(companyId!=paramOrder.getCompany().getId()) return false;
            paramOrder.setId(id);
            paramOrder.setReport(report);
            paramOrder.setRemark(remark);
            //提交报告状态
            paramOrder.setStatus(2);
            //调试用
            Order returnOrder= orderService.saveOrder(paramOrder);
            return  null!=returnOrder;
        }catch (Exception e){
            return false;
        }
    }
    @RequestMapping(value = "/findAllOrderByCompanyId/{companyId}")
    public List<Order> findAllOrderByCompanyId(@PathVariable Long companyId){
        List<Order> orderList=orderService.findAllOrderByCompanyId(companyId);
        return orderList;
    }

    @RequestMapping(value = "/findOrderByCondition")
    public List<Order> findOrderByCondition(@RequestBody Map paramMap){
        Long companyId= ((Integer)paramMap.get("companyId")).longValue();
        Integer status=(Integer)paramMap.get("status");
        List<Order> allOrderByCompanyIdAndStatus = orderService.findAllOrderByCompanyIdAndStatus(companyId,status);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat();
        c.setTimeInMillis((Long)paramMap.get("startDate"));
        Date startDate = c.getTime();
        System.out.println("$$$$$$$$$$startDate"+sdf.format(startDate));
        c.setTimeInMillis((Long)paramMap.get("endDate"));
        Date endDate=c.getTime();
        System.out.println("$$$$$$$$$$endDate"+sdf.format(endDate));
        List<Order> temp_list= new LinkedList<>();
        for (Order order:allOrderByCompanyIdAndStatus
             ) {
            if(order.getAppointmentTime().compareTo(startDate)>0&&order.getAppointmentTime().compareTo(endDate)<0)
            temp_list.add(order);
        }
        return temp_list;
    }
}
