package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.ManagerDispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerDispatchOrderController {
    @Autowired
    private ManagerDispatchOrderService managerDispatchService;

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    @ResponseBody
    @RequestMapping("/findAllOrderByType")
    public String findAllOrderByType(SentParameters sentParameters,Integer type){
        System.out.println("qqqqqqqqqqqqqqqqqqqqq"+type);
        System.out.println(managerDispatchService.findAllOrderByType(sentParameters , type)+"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        return managerDispatchService.findAllOrderByType(sentParameters , type);

    }

    //跳转添加检测报告页 lqj add 2018-2-6
    @RequestMapping("/toAddPicture/{id}")
    public String toAddPicture( @PathVariable("id")Integer id){
        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZ"+id);
        return "dispatch/addPicture";
    }

}
