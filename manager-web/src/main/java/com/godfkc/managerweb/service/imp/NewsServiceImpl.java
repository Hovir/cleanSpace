package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String select(SentParameters sentParameters) {
        String url = centerUrl + "news/selectNews";
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }

    @Override
    public void inserNews(Map<String, Object> map) {
        String url = centerUrl + "news/insertNews";
        this.restTemplate.postForObject(url, map, String.class);
    }

    @Override
    public Boolean deleteNews(List<Long> id) {
        String url = centerUrl + "news/deleteNews";
        return this.restTemplate.postForObject(url, id, Boolean.class);
    }

    @Override
    public String selectNewsById(Long id) {
        String url = centerUrl + "news/selectNewsById/{id}";
        return this.restTemplate.getForObject(url, String.class, id);
    }

    @Override
    public void saveUpdateNewsById(Map<String, Object> map) {
        String url = centerUrl + "news/saveUpdateNewsById";
        this.restTemplate.postForObject(url, map, String.class);
    }
}
