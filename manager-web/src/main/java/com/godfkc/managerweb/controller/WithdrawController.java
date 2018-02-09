package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @ResponseBody
    @RequestMapping("/withdrawList")
    public String withdrawList(SentParameters sentParameters){
        String json = withdrawService.findWithdrawList(sentParameters);
        return json;
    }

    @ResponseBody
    @RequestMapping("/updatePay/{id}")
    public void updatePay(@PathVariable("id") Long id){
        withdrawService.updatePay(id);
    }
}
