package com.godfkc.mobileweb.controller;

import com.godfkc.common.pojo.mobile.OrderVo;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.OrderService;
import com.godfkc.mobileweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 18:38 2018/2/1
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Value("${session.key.userPhone}")
    private String sessionKeyUserPhone;

    /**
     * 我的预约
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/myReservation")
    @ResponseBody
    public String myReservation(HttpServletRequest request, Model model){
        String companyId = "4";
        String userId = null;
        String orderListJson = orderService.findByCompanyIdOrUserId(companyId, userId);
        //System.out.println("返回第一个controller:"+orderListJson);
        return orderListJson;
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public String addOrder(HttpServletRequest request, OrderVo orderVo){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            Long id=userService.selectUserIdByPhone(phone);
            orderVo.setStatus(1);
            orderVo.setUserId(id);
            System.out.println(orderVo.getAppointmentTime());
            if(orderService.addOrder(orderVo)){
                return "1";
            }else {
                return "2";
            }
        }else {
            return "3";
        }
    }

    /**
     * 我的预约详情页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/customerDetails")
    public String customerDetails(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        String orderById = orderService.findOrderById(id);
        Map<String, Object> map = JsonUtils.JsonToMap(orderById);
        Long appointmentTime = (Long) map.get("appointmentTime");
        Date date = new Date(appointmentTime);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf1.format(date);
        map.put("appointmentTime",format);
        model.addAttribute("order",map);
        return "details of customer";
    }
}
