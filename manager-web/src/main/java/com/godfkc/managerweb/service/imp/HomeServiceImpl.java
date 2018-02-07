package com.godfkc.managerweb.service.imp;

import com.godfkc.managerweb.service.HomeService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public Map<String , Object> message() {
        String url = centerUrl + "home/message";
        return new Gson().fromJson(restTemplate.getForObject(url, String.class) , new TypeToken<Map<String , Object>>() {}.getType());
    }
}
