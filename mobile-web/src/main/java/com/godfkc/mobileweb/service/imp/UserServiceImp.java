package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

/**
 * @author wj
 * @date 10:37 2018/1/31
 * @description
 */
@Service
@Transactional
@PropertySource("classpath:config.properties")
public class UserServiceImp implements UserService{
    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;
    @Override
    public String findByPhoneAndPassword(String phone, String password) {
        String url = centerUrl + "login/{phone}/{password}";
        System.out.println(url+"-----------------------------------------");
        HashMap<String,String> map = new HashMap<>(16);
        map.put("phone",phone);
        map.put("password",password);
        String user = this.restTemplate.getForObject(url, String.class, map);
        return user;
    }

    @Override
    public String doLogin(String phone, String password) {
        String url = centerUrl + "doLogin/{phone}/{password}";
        HashMap<String,String> map = new HashMap<>(16);
        map.put("phone",phone);
        map.put("password",password);
        String user = this.restTemplate.getForObject(url, String.class, map);
        return user;
    }

    @Override
    public Long selectUserIdByPhone(String phone) {
        String url = centerUrl + "selectUserIdByPhone/{phone}";
        HashMap<String,String> map = new HashMap<>(16);
        map.put("phone",phone);
        Long id = this.restTemplate.getForObject(url, Long.class, map);
        System.out.println(id);
        return id;
    }

    @Override
    public boolean insertUserInfo(String phone, String password) {
        String url = centerUrl +"saveUserInfo";
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",password);
        map.put("name","user");
        map.put("headImg","/img/center.png");
        map.put("createTime",new Date());
        return this.restTemplate.postForObject(url,map,boolean.class);
    }

    @Override
    public boolean updatePwdByPhone(String phone, String password) {
        String url = centerUrl + "updatePwdByPhone";
        HashMap<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",password);
        return this.restTemplate.postForObject(url,map,boolean.class);
    }


}
