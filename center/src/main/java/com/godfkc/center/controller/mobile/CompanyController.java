package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.*;
import com.godfkc.center.service.mobile.CompanyService;
import com.godfkc.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping("/company/selectUnderCompany/{companyId}")
    public List<Company> selectUnderCompany(@PathVariable(value = "companyId")Long companyId){
        List<Company> companyList=companyService.selectUnderCompany(companyId);
        return companyList;
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
                if (cfl.getType() == 2) {
                    sumMoney += cfl.getMoney();
                    //更新时间晚于今日起始,获取今日收入总金额
                    if (cfl.getUpdateTime().compareTo(time_day_starter) > 0) {
                        moneyToday += cfl.getMoney();
                    }
                }
            }
            //获取公司订单
            Set<Order> orders = company.getOrders();
            Set<Order> ordersToday = new HashSet<>();
            for (Order order:orders
                 ) {
                if(order.getAppointmentTime().compareTo(time_day_starter)>0){
                    ordersToday.add(order);
                }
            }
            returnMap.put("ordersToday",ordersToday);
            //returnMap.put("orders", orders);
            returnMap.put("sumMoney", sumMoney);
            returnMap.put("moneyToday", moneyToday);
            returnMap.put("companyImg", company.getImgUrl());
        }
        return returnMap;

    }

    /**
     * 根据公司id判断是否绑定银行卡
     * @param companyId
     * @return
     */
    @RequestMapping("/findByCompanyId")
    public CompanyBankCard findByCompanyId(@RequestBody Long companyId){
        return companyService.findByCompanyId(companyId);
    }

    /**
     * 解绑银行卡
     * @param bankCardId
     * @return
     */
    @RequestMapping("/unbindMod")
    public boolean unbindMod(@RequestBody Long bankCardId){
        int i = companyService.unbindMod(bankCardId);
        if(i == 1){
            return true;
        }
        return false;
    }

    /**
     * 查询所有银行
     * @return
     */
    @RequestMapping("/findBankDictAll")
    public List<BankDict> findBankDictAll(){
        return companyService.findBankDictAll();
    }

    /**
     * 绑定银行卡
     * @param map
     * @return
     */
    @RequestMapping("/bindBankCard")
    public CompanyBankCard bindBankCard(@RequestBody Map map){
        CompanyBankCard companyBankCard = new CompanyBankCard();
        Company company = new Company();
        company.setId(Long.parseLong(map.get("compayId").toString()));
        BankDict bankDict = new BankDict();
        bankDict.setId(Long.parseLong(map.get("bankDictId").toString()));
        companyBankCard.setCardNo((String) map.get("cardNo"));
        companyBankCard.setUsername((String)map.get("username"));
        companyBankCard.setPhone((String)map.get("phone"));
        companyBankCard.setCompany(company);
        companyBankCard.setBankDict(bankDict);
        companyBankCard.setStatus("1");
        return companyService.bindBankCard(companyBankCard);
    }


    @RequestMapping("/selectCompanies/{levelId}/{state}/{city}/{district}")
    public List<Company> selectCompanies(@PathVariable(value = "levelId") Long levelId,@PathVariable(value = "state") String state,@PathVariable(value = "city") String city,@PathVariable(value = "district") String district){
        List<Company> companyList=companyService.selectCompanies(levelId,state,city,district);
        return companyList;
    }
    /**
     * 查询余额
     * @param companyId
     * @return
     */
    @RequestMapping("/findCompanyFundsByCompanyId")
    public CompanyFunds findCompanyFundsByCompanyId(@RequestBody Long companyId){
        return companyService.findCompanyFundsByCompanyId(companyId);
    }

    /**
     * 根据企业id查询CompanyBankCard
     * @param companyId
     * @return
     */
    @RequestMapping("/findCompanyBankCardByCompanyId")
    public CompanyBankCard findCompanyBankCardByCompanyId(@RequestBody Long companyId){
        return companyService.findCompanyBankCardByCompanyId(companyId);
    }

    /**
     * 提现改变余额
     * @param map
     * @return
     */
    @RequestMapping("/changeBalance")
    public boolean changeBalance(@RequestBody Map map){
        Long money = Long.parseLong(map.get("money").toString());
        Long companyId = Long.parseLong(map.get("companyId").toString());
        int i = companyService.changeBalance(money, new Date(), companyId);
        if (i == 1){
            return true;
        }
        return false;
    }

    /**
     * 插入提现表
     * @param map
     * @return
     */
    @RequestMapping("/insertFundsWithdraw")
    public CompanyFundsWithdraw insertFundsWithdraw(@RequestBody Map map){
        CompanyFundsWithdraw companyFundsWithdraw = new CompanyFundsWithdraw();
        Company company = new Company();
        company.setId(Long.parseLong(map.get("companyId").toString()));
        companyFundsWithdraw.setCreateTime(new Date());
        companyFundsWithdraw.setIsPay(0);
        companyFundsWithdraw.setMoney(Long.parseLong(map.get("money").toString()));
        companyFundsWithdraw.setStatus(1);
        companyFundsWithdraw.setUpdateTime(new Date());
        companyFundsWithdraw.setCompany(company);
        return companyService.insertFundsWithdraw(companyFundsWithdraw);
    }


    @RequestMapping("/insertFundsLog")
    public CompanyFundsLog insertFundsLog(@RequestBody Map map){
        Company company = new Company();
        company.setId(Long.parseLong(map.get("companyId").toString()));
        CompanyFundsLog companyFundsLog = new CompanyFundsLog();
        companyFundsLog.setCompany(company);
        companyFundsLog.setCreateTime(new Date());
        companyFundsLog.setCurrentMoney(Long.parseLong(map.get("currentMoney").toString()));
        companyFundsLog.setDescreption((String)map.get("descreption"));
        companyFundsLog.setMoney(Long.parseLong(map.get("money").toString()));
        companyFundsLog.setType((int)map.get("type"));
        companyFundsLog.setUpdateTime(new Date());
        return companyService.insertFundsLog(companyFundsLog);
    }

    @RequestMapping("/findAllByCompanyId/{companyId}")
    public List<CompanyFundsLog> findAllByCompanyId(@PathVariable Long companyId){
        return companyService.findAllByCompanyId(companyId);
    }

    @RequestMapping("/selectLevelIdByCompanyId/{companyId}")
    public Long selectLevelIdByCompanyId(@PathVariable Long companyId){
        return companyService.selectLevelIdByCompanyId(companyId);
    }
}
