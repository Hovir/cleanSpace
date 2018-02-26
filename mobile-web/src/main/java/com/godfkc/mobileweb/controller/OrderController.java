package com.godfkc.mobileweb.controller;

import com.godfkc.common.pojo.mobile.OrderVo;
import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.CardService;
import com.godfkc.mobileweb.service.OrderService;
import com.godfkc.mobileweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private CardService cardService;

    @Value("${session.key.userPhone}")
    private String sessionKeyUserPhone;

    @Value("${session.key.companyId}")
    private String sessionKeyCompanyId;

    /**
     * 我的预约
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/myReservation")
    @ResponseBody
    public String myReservation(HttpServletRequest request, Model model){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if(phone!=null&&phone.length()>0){
            Long userId=userService.selectUserIdByPhone(phone);
            String orderListJson = orderService.findByCompanyIdOrUserId(companyId, userId);
            //System.out.println("返回第一个controller:"+orderListJson);
            return orderListJson;
        }
        return "1";
    }

    //预约检测
    @RequestMapping("/addOrder")
    @ResponseBody
    public String addOrder(HttpServletRequest request, OrderVo orderVo){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            Long id=userService.selectUserIdByPhone(phone);
            orderVo.setStatus(1);
            orderVo.setUserId(id);
            String json=cardService.selectCardByUserId(id);
            Map<String, Object> map = JsonUtils.JsonToMap(json);
            Integer cardId1 = (Integer) map.get("id");
            Long cardId=Long.parseLong(cardId1.toString());
            int times= (int) map.get("times");
            int times1=times-1;
            if(times>0){
                if(orderService.addOrder(orderVo)){
                    if(cardService.updateCardTimes(cardId,times1)){
                        return "1";
                    }else {
                        return "2";
                    }
                }else {
                    return "2";
                }
            }else {
                return "4";
            }
        }else {
            return "3";
        }
    }

    //预约治理
    @RequestMapping("/addOrder1")
    @ResponseBody
    public String addOrder1(HttpServletRequest request, OrderVo orderVo){
        String phone = (String) request.getSession().getAttribute(sessionKeyUserPhone);
        if(phone!=null&&phone.length()>0){
            Long id=userService.selectUserIdByPhone(phone);
            orderVo.setStatus(1);
            orderVo.setUserId(id);
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

    /**
     * 更新订单报告、留言、状态
     * @param orderVo
     * @return
     */
    @RequestMapping("/updateOrderInfo")
    public String updateOrderInfo(OrderVo orderVo){
        boolean flag=orderService.updateReportAndRemark(orderVo.getId().longValue(),orderVo.getCompanyId().longValue(),orderVo.getRemark(),orderVo.getReport());
        System.out.println(orderVo);
        if(flag) System.out.println("success");
        return "redirect :/company/myCompany";
    }

    /**
     * 条件查询预约
     */
    @RequestMapping("/orderFilter/{status}/{date1}/{date2}")
    public String orderFilter(HttpServletRequest request, @PathVariable Integer status,@PathVariable String date1,@PathVariable String date2)  {
        System.out.println(status);
        System.out.println(date1);
        System.out.println(date2);

        Long companyId = (Long) request.getSession().getAttribute(sessionKeyCompanyId);
        if (null != companyId) {
            Map<String,Object> param_map=new HashMap<>();
            param_map.put("companyId",companyId);
            param_map.put("status",status);
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date startDate= null;
            Date endDate= null;
            try{//parse string to date
            startDate=sdf.parse(date1);
            endDate=sdf.parse(date2);
            }catch (Exception e){
                System.out.println("Error when parsing string to date");
            }
            param_map.put("startDate",startDate);
            param_map.put("endDate",endDate);
            String json_orderList =  orderService.findOrderByCondition(param_map);
            System.out.println("test json: ****** "+json_orderList);
            request.setAttribute("orderList", json_orderList);
            return "detection_and_management";
        } else {
            return "com-login";
        }
    }
}
