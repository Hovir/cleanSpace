package com.godfkc.managerweb.service.imp;

import com.godfkc.managerweb.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class PictureServiceImpl implements PictureService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public void webUploader(List<String> imgUrl) {
        String url = centerUrl + "picture/webUploader";
        this.restTemplate.postForObject(url, imgUrl, String.class);
    }
}
