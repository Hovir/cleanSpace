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

    //通过状态（type）查询派遣订单表(order) lqj add 2018-2-1
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

    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    @RequestMapping("/updateOrderReport/{report}/{id}")
    public boolean updateOrderReport(@PathVariable("report") String report,@PathVariable("id") Long id){
        boolean b = dispatchOrderService.updateOrderReport(report, id);
        if(b==true){
            return true;
        }else{
            return false;
        }

    }

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    @RequestMapping(value = "/findOrderByTypeAndStatus/{type}/{status}",method = RequestMethod.POST)
    public ReturnedData findOrderByTypeAndStatus(@RequestBody SentParameters sentParameters,@PathVariable("type") Integer type,@PathVariable("status") Integer status){
        System.out.println("ccccccccccccccccccccccccccccccc"+type);
        System.out.println(sentParameters.getDraw());
        //返回到前台的数据
        ReturnedData<Order> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);
        Page<Order> page = dispatchOrderService.findAllOrderByTypeAndStatus(type ,status, dataTablesReturn.getPage() , dataTablesReturn.getSize() ,dataTablesReturn.getDir(),dataTablesReturn.getData(),dataTablesReturn.getSearch());
        System.out.println("------------------------"+page.getContent().get(0).getUser().getId());
        System.out.println("###"+page);
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());
        return returnedData;
    }

    /**
     * 添加公司
     * @param ztreeId
     * @param id
     */
    @ResponseBody
    @RequestMapping("saveCompany/{ztreeId}/{id}")
    public void saveCompany(@PathVariable("ztreeId") Long ztreeId,@PathVariable("id") Long id){
        dispatchOrderService.saveCompany(ztreeId,id);
    }

    @RequestMapping(value = "/dispatch/updateReprot",method ={RequestMethod.POST} )
    public void updateReprot(@RequestBody Order order){
        dispatchOrderService.updateReprot(order);
    }
}
