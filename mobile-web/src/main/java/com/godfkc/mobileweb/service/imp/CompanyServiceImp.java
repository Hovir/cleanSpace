package com.godfkc.mobileweb.service.imp;

import com.godfkc.common.utils.JsonUtils;
import com.godfkc.mobileweb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public String findByNameAndPassword(String name,String password) {
        String url=centerUrl+"company/doLogin/{name}/{password}";
        System.out.println("url of \"findBynameAndPassword\" in company service: "+url);

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
        return result;
    }
}