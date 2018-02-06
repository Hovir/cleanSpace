package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
    public boolean bindBankCard(String cardNo, String phone, Long compayId, String bankDictId) {
        String url = centerUrl + "bindBankCard";
        Map<String,Object> map = new HashMap<>();
        map.put("cardNo",cardNo);
        map.put("phone",phone);
        map.put("compayId",compayId);
        map.put("bankDictId",Long.parseLong(bankDictId));
        String s = restTemplate.postForObject(url,map,String.class);
        if (s != null){
            return true;
        }
        return false;
    }


}
