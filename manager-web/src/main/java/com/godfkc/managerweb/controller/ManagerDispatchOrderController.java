package com.godfkc.managerweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.managerweb.service.ManagerDispatchOrderService;
import org.apache.catalina.WebResource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    //异步加载检测图片 lqj add 2018-2-6
    @ResponseBody
    @RequestMapping("/addPicture")
    public Map<String, Object> addPicture(@RequestParam(required = false) MultipartFile[] pic){
        NewsController newsController = new NewsController();
        List<String> strings = newsController.fileUpload(pic);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+strings.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", strings);
        return map;
    }

    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    @RequestMapping("/updateOrderReport")
    public String updateOrderReport(String imgUrl,Long orderId){
        System.out.println("managerControllerImgUrl::::"+imgUrl);
        System.out.println("managerControllerOrderId::::"+orderId);
        managerDispatchService.updateOrderReport(imgUrl,orderId);
        return "dispatch/govern";
    }

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    @ResponseBody
    @RequestMapping("/findOrderByTypeAndStatus")
    public String findOrderByTypeAndStatus(SentParameters sentParameters,Integer type,Integer status){
        System.out.println("type::::::::::::::::::::::::::"+type);
        System.out.println("status::::::::::::::::::::::::"+status);
        return managerDispatchService.findOrderByTypeAndStatus(sentParameters,type,status);
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
