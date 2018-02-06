package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author syx
 * @date 11:13 2018-2-6
 * @description
 */
@Service
@Transactional
@PropertySource("classpath:config.properties")
public class NewsServiceImpl implements NewsService{

    @Autowired
    RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String selectNews() {
        String url = centerUrl + "selectNews";
        String json = this.restTemplate.getForObject(url, String.class);
        System.out.println(json);
        return json;
    }
}
