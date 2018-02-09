package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.ContentService;
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
public class ContentServiceImpl implements ContentService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String contentLis(SentParameters sentParameters) {
        String url = centerUrl + "content/contentList";
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }

    @Override
    public Boolean delect(List<Long> id) {
        String url = centerUrl + "content/deleteContent";
        return this.restTemplate.postForObject(url, id, Boolean.class);
    }

    @Override
    public void insertContent(Map<String, Object> map) {
        String url = centerUrl + "content/insertContent";
        this.restTemplate.postForObject(url, map, String.class);
    }

    @Override
    public String openUpdateContent(long id) {
        String url = centerUrl + "content/selectUpdateContent/{id}";
        return this.restTemplate.getForObject(url, String.class, id);
    }

    @Override
    public void saveUpdateContent(Map<String, Object> map) {
        String url = centerUrl + "content/saveUpdateContent";
        this.restTemplate.postForObject(url, map, String.class);
    }
}
