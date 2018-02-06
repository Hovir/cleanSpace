package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author syx
 * @date 16:58 2018-2-5
 * @description
 */

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class CardServiceImpl implements CardService{

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;


    @Override
    public boolean selectUserCard(Long id) {
        String url = centerUrl + "selectUserCard/{userId}";
        boolean flag = this.restTemplate.getForObject(url, boolean.class, id);
        return flag;
    }

    @Override
    public String checkCard(String num, String password) {
        String url = centerUrl + "checkCard/{num}/{password}";
        Map<String,Object> map = new HashMap<>();
        map.put("num",num);
        map.put("password",password);
        String json = this.restTemplate.getForObject(url, String.class, map);
        return json;
    }
}
