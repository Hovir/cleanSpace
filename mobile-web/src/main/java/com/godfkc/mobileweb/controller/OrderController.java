package com.godfkc.mobileweb.controller;

import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
