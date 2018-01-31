package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.NewsVo;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public void addNews(NewsVo newsVo) {
        String url = centerUrl + "news/insertNews";
        this.restTemplate.postForObject(url, newsVo, String.class);
    }

    @Override
    public String select(SentParameters sentParameters) {
        String url = centerUrl + "news/selectNews";
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }
}
