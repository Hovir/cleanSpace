package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.*;
import com.godfkc.center.service.mobile.CompanyService;
import com.godfkc.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @param:
 * @return:
 * @author: Qi Zining
 * @date: 2018/2/1
 */
@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /*
     * Author: Qi Zining
     * Date: 2018/2/2
     * Description: 企业登录校验
     */
    @RequestMapping(value = "/companyLoginCheck/{name}/{password}", method = RequestMethod.POST)
    public Company login(@PathVariable(value = "name") String name, @PathVariable(value = "password") String password) {
        System.out.println("in RestController, param are:  name = " + name + " password = " + password);
        Company company = companyService.findByNameAndPassword(name, password);
        return company;
    }


    @RequestMapping("/companyTest")
    public String test() {
        System.out.println("come in");
        return "b";
    }

    @RequestMapping(value = "/getCompanyDetailByname/{name}", method = RequestMethod.POST)
    public Map<String, Object> findCompanyDetailByName(@PathVariable String name) {
        System.out.println("name of  /getCompanyDetailByname/{name}  " + name);
        //返回值对象
        Map<String, Object> returnMap = new HashMap<>();
        Long id = 0L;//公司id
        Long money = 0L;//公司剩余资金
        Long sumMoney = 0L;//公司总收入
        Long moneyToday = 0L;//公司今日收入
        Company company = companyService.findByName(name);
        if (null != company) {
            //获取公司id
            id = company.getId();
            returnMap.put("id", id);
            Set<CompanyFunds> companyFundsSet = company.getCompanyFundsSet();

            for (CompanyFunds cf : companyFundsSet
                    ) {
                //获取公司剩余金额
                money = cf.getMoney();
            }
            returnMap.put("money", money);
            //获取公司订单
            Set<Order> orders = company.getOrders();
            returnMap.put("orders", orders);

            //获取公司总收入

            Set<CompanyFundsLog> companyFundsLogs = company.getCompanyFundsLogs();

            //获取今日零时时间
            Calendar dayStarter = Calendar.getInstance();
            dayStarter.set(Calendar.HOUR_OF_DAY, 0);
            dayStarter.set(Calendar.MINUTE, 0);
            dayStarter.set(Calendar.SECOND, 0);
            dayStarter.set(Calendar.MILLISECOND, 0);
            Date time_day_starter = dayStarter.getTime();
            for (CompanyFundsLog cfl : companyFundsLogs
                    ) {
                if (cfl.getType() == 1) {
                    sumMoney += cfl.getMoney();
                    //更新时间晚于今日起始,获取今日收入总金额
                    if (cfl.getUpdateTime().compareTo(time_day_starter) > 0) {
                        moneyToday += cfl.getMoney();
                    }
                }
            }
            returnMap.put("sumMoney", sumMoney);
            returnMap.put("moneyToday", moneyToday);
        }
        return returnMap;

    }
}
