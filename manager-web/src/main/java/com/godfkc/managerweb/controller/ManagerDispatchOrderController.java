package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.ManagerDispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        String json = managerDispatchService.findAllOrderByType(sentParameters , type);
        return json;
    }

    //跳转添加检测报告页 lqj add 2018-2-6
    @RequestMapping("/toAddPicture/{id}")
    public String toAddPicture( @PathVariable("id")Integer id,Model model){
        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZ"+id);
        model.addAttribute("id",id);
        return "dispatch/addPicture";
    }

    //跳转添加检测公司 zhaozhb
    @RequestMapping("/addCompany/{id}")
    public String addCompany(@PathVariable("id")Integer id, Model model){
        model.addAttribute("id",id);
        return "dispatch/deteCompany";
    }

    //添加派遣公司 zhaozhb
    @ResponseBody
    @RequestMapping("saveDeteCompany/{ztreeId}/{id}")
    public void saveDeteCompany(@PathVariable("ztreeId")Long ztreeId,@PathVariable("id")Long id){
        managerDispatchService.saveCompany(ztreeId,id);
    }

    //上传报告
    @RequestMapping("dispatch/addPicture")
    public void addPicture(String report,Long id,Model model){
        model.addAttribute("id",id);
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("report",report);
        managerDispatchService.updateReprot(map);
    }

}
