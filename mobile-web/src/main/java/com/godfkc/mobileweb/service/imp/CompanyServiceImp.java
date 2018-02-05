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
        System.out.println("url of \"findBynameAndPassword\" in company service: "+url+"params are :"+name+"**"+password);
        String test = restTemplate.postForObject(centerUrl + "companyTest",null,String.class);
        System.out.println("test b: "+test);

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
        System.out.println("result is :========="+result);

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
}
