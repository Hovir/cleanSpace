package com.godfkc.mobileweb.controller;

import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
        String userId = "1";
        String orderListJson = orderService.findByCompanyIdOrUserId(companyId, userId);
        System.out.println("返回第一个controller:"+orderListJson);
        return orderListJson;
    }
}
