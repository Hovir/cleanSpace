package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.manager.CardVo;
import com.godfkc.managerweb.service.CardService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Transactional
@PropertySource("classpath:config.properties")
public class CardServiceImpl implements CardService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String allCompany() {
        String url = centerUrl + "card/selectLevel";
        return restTemplate.postForObject(url, null, String.class);
    }

    @Override
    public List<CardVo> insertCard(long id, Integer num) {
        String url = centerUrl + "card/insertCard/{id}/{num}";
        String json = restTemplate.getForObject(url, String.class, id, num);
        return new Gson().fromJson(json , new TypeToken<List<CardVo>>(){}.getType());
    }
}
