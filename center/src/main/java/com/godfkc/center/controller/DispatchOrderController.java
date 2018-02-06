package com.godfkc.center.controller;

import com.godfkc.center.entity.News;
import com.godfkc.center.entity.Order;
import com.godfkc.center.service.DispatchOrderService;
import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.dataTables.ReturnedDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单信息
 * lqj add 2018-2-1
 */

@RestController
public class DispatchOrderController {

    @Autowired
    private DispatchOrderService dispatchOrderService;

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    @RequestMapping(value = "/findAllOrderByStatus/{type}",method = RequestMethod.POST)
    public ReturnedData findAllOrderByStatus(@RequestBody SentParameters sentParameters,@PathVariable("type") Integer type){
        System.out.println("ccccccccccccccccccccccccccccccc"+type);
        System.out.println(sentParameters.getDraw());
        //返回到前台的数据
        ReturnedData<Order> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);
        Page<Order> page = dispatchOrderService.findAllOrderByType(type , dataTablesReturn.getPage() , dataTablesReturn.getSize() ,dataTablesReturn.getDir(),dataTablesReturn.getData(),dataTablesReturn.getSearch());
        System.out.println("------------------------"+page.getContent().get(0).getUser().getId());
        System.out.println("###"+page);
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());
        return returnedData;
    }

    @RequestMapping("/findAllOrderByStatusTest/{status}")
    public List findAllOrderByStatusTest(@PathVariable("status") Integer status){
        List<Order> orderByStatus = dispatchOrderService.findOrderByStatus(status);
        System.out.println("----------------------------------"+orderByStatus.get(0).getName());
        System.out.println("=+++++++++++++++++++++++++++++++++"+orderByStatus.get(0).getUser().getId().toString());
        return orderByStatus;
    }

}
