package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.HeadPageImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author syx
 * @date 17:42 2018-2-7
 * @description
 */
@Service
@Transactional
@PropertySource("classpath:config.properties")
public class HeadPageImgServiceImpl implements HeadPageImgService{
    @Autowired
    RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String selectHeadPageImg() {
        String url= centerUrl+"selectHeadPageImg";
        String json = restTemplate.getForObject(url,String.class);
        return json;
    }
}
