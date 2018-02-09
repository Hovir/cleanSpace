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
        return json;
    }

    @Override
    public String selectNewsById(Long newId) {
        String url = centerUrl + "selectNewsById/{newId}";
        String json = this.restTemplate.getForObject(url,String.class,newId);
        System.out.println(json);
        return json;
    }

    @Override
    public String selectContentByCount(Integer count) {
        String url = centerUrl + "selectNewsByCount/{count}";
        String json = this.restTemplate.getForObject(url,String.class,count);
        System.out.println("json of content list::"+json);
        return json;
    }

    @Override
    public String selectContentById(Long contentId) {
        String url = centerUrl + "selectContentById/{contentId}";
        String json = this.restTemplate.getForObject(url,String.class,contentId);
        System.out.println("content::"+json);
        return json;
    }
}
