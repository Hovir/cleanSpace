package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class CompanyServiceImp implements CompanyService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;
    @Override
    public boolean findByNameAndPassword(String name,String password) {
        String url=centerUrl+"companyLoginCheck/{name}/{password}";

        Map<String ,Object> paramMap = new HashMap();
        paramMap.put("name",name);
        paramMap.put("password",password);
        /*
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String strParam = JsonUtils.Object2Json(paramMap);
        HttpEntity<String> formEntity = new HttpEntity(strParam, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        */
        String result = restTemplate.postForObject(url, null, String.class, paramMap);
        return null != result && "" != result.trim();
    }

    @Override
    public String findCompanyDetailByName(String companyName) {
        String url= centerUrl+"/getCompanyDetailByname/{name}";
        Map paramMap= new HashMap();
        paramMap.put("name",companyName);
        String result = restTemplate.postForObject(url,null,String.class,paramMap);
        return result;
    }

    @Override
    public String selectUnderCompany(Long id) {
        String url=centerUrl+"company/selectUnderCompany/{companyId}";
        Map<String ,Object> map = new HashMap();
        map.put("companyId",id);
        String json = restTemplate.getForObject(url,String.class, map);
        System.out.println(json);
        return json;
    }

    @Override
    public String findByCompanyId(Long companyId) {
        String url = centerUrl + "findByCompanyId";
        String json = restTemplate.postForObject(url,companyId,String.class);
        System.out.println("查询的绑定的企业银行卡："+json);
        return json;
    }

    @Override
    public boolean unbindMod(Long bankCardId) {
        String url = centerUrl + "unbindMod";
        return restTemplate.postForObject(url,bankCardId,boolean.class);
    }

    @Override
    public String findBankDictAll() {
        String url = centerUrl + "findBankDictAll";
        String bankDictJson = restTemplate.getForObject(url, String.class);
        System.out.println("返回的所有银行信息：" + bankDictJson);
        return bankDictJson;
    }

    @Override
    public boolean bindBankCard(String cardNo, String phone, Long compayId, String bankDictId,String username) {
        String url = centerUrl + "bindBankCard";
        Map<String,Object> map = new HashMap<>();
        map.put("cardNo",cardNo);
        map.put("phone",phone);
        map.put("compayId",compayId);
        map.put("bankDictId",Long.parseLong(bankDictId));
        map.put("username",username);
        String s = restTemplate.postForObject(url,map,String.class);
        if (s != null){
            return true;
        }
        return false;
    }

    @Override
    public String findCompanyFundsByCompanyId(Long companyId) {
        String url = centerUrl + "findCompanyFundsByCompanyId";
        return restTemplate.postForObject(url,companyId,String.class);
    }

    @Override
    public String findCompanyBankCardByCompanyId(Long companyId) {
        String url = centerUrl + "findCompanyBankCardByCompanyId";
        String s = restTemplate.postForObject(url,companyId,String.class);
        System.out.println("返回的企业银行卡json:"+s);
        return s;
    }

    @Override
    public boolean changeBalance(int money, Long companyId) {
        String url = centerUrl + "changeBalance";
        Map<String,Object> map = new HashMap<>();
        map.put("money",money);
        map.put("companyId",companyId);
        return restTemplate.postForObject(url,map,boolean.class);
    }

    @Override
    public boolean insertFundsWithdraw(String withdrawlMoney, Long companyId) {
        String url = centerUrl + "insertFundsWithdraw";
        Map<String,Object> map = new HashMap<>();
        map.put("money",withdrawlMoney);
        map.put("companyId",companyId);
        String s = restTemplate.postForObject(url, map, String.class);
        if (s != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean insertFundsLog(int afterWithdrawInt, String withdrawlMoney, Long companyId,String descreption,Integer type) {
        String url = centerUrl + "insertFundsLog";
        Map<String,Object> map = new HashMap<>();
        map.put("currentMoney",afterWithdrawInt);
        map.put("money",withdrawlMoney);
        map.put("companyId",companyId);
        map.put("descreption",descreption);
        map.put("type",type);
        String s = restTemplate.postForObject(url, map, String.class);
        if (s != null){
            return true;
        }
        return false;
    }

    @Override
    public String findFundsLogByCompanyId(Long companyId) {
        String url = centerUrl + "findAllByCompanyId/{companyId}";
        String fundsLogList = restTemplate.getForObject(url, String.class, companyId);
        System.out.println("明细json数据:"+fundsLogList);
        return fundsLogList;
    }

    @Override
    public String selectCompanies(Long levelId, String state, String city, String district) {
        String url = centerUrl + "selectCompanies/{levelId}/{state}/{city}/{district}";
        Map<String,Object> map = new HashMap<>();
        map.put("levelId",levelId);
        map.put("state",state);
        map.put("city",city);
        map.put("district",district);
        String json = restTemplate.getForObject(url,String.class,map);
        System.out.println(json);
        return json;
    }


}
