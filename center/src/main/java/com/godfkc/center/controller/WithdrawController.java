package com.godfkc.center.controller;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyFundsWithdraw;
import com.godfkc.center.service.WithdrawService;
import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.dataTables.ReturnedDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/withdraw/")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @RequestMapping(value = "withdrawList",method = {RequestMethod.POST})
    public ReturnedData withdrawList(@RequestBody SentParameters sentParameters){
        ReturnedData<CompanyFundsWithdraw> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);
        Page<CompanyFundsWithdraw> page = withdrawService.getWithdrawList(dataTablesReturn.getPage(), dataTablesReturn.getSize(), dataTablesReturn.getDir(), dataTablesReturn.getData(), dataTablesReturn.getSearch());
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());

        return returnedData;
    }

    @RequestMapping("updatePay/{id}")
    public void updatePay(@PathVariable("id") Long id){
        withdrawService.updatePay(id);

    }
}
