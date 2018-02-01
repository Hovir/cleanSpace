package com.godfkc.managerweb.service.imp;

import com.godfkc.managerweb.service.AdminService;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${center.url}")
    private  String centerUrl;

    @Override
    public Boolean getAdminLogin(String name, String password) {
        String url=centerUrl+"admin/doLogin/{name}/{password}";
        Map<String,String> adminMap=new HashMap<String,String>();
        adminMap.put("name",name);
        adminMap.put("password",password);
        String adminString=this.restTemplate.postForObject(url,null,String.class,adminMap);
        if (adminString!=null){
            return true;
        }
        return false;
    }
}
