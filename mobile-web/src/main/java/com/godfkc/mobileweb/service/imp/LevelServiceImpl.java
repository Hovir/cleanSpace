package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author syx
 * @date 17:41 2018-2-6
 * @description
 */
@Service
@Transactional
@PropertySource("classpath:config.properties")
public class LevelServiceImpl implements LevelService{
    @Autowired
    RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;


    @Override
    public String selectLevels() {
        String url = centerUrl + "selectLevels";
        String json = this.restTemplate.getForObject(url, String.class);
        System.out.println(json);
        return json;
    }

    @Override
    public Long selectCommisionByLevelId(Long levelId) {
        String url = centerUrl + "selectCommisionByLevelId/{levelId}";
        Long commision= this.restTemplate.getForObject(url,Long.class,levelId);
        return commision;
    }
}
