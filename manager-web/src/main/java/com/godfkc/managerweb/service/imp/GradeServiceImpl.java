package com.godfkc.managerweb.service.imp;

import com.godfkc.managerweb.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String findAll() {
        String url=centerUrl+"grade/selectGrade";
        String forObject = this.restTemplate.getForObject(url,String.class);
        return forObject;
    }

    /*
     * 修改佣金
     * */
    @Override
    public boolean updataCommision(Long commision, Long id) {
        String url=centerUrl+"grade/updataCommision/{commision}/{id}";
        Map<String,Object> map=new HashMap<>();
        map.put("commision",commision);
        map.put("id",id);
        boolean a = this.restTemplate.getForObject(url, boolean.class, map);
        return a;
    }

    /*
        *根据id查询数据
        * */
    @Override
    public Map<String,Object> selectById(Long id) {
        String url=centerUrl+"grade/selectById/{id}";

       return this.restTemplate.getForObject(url, Map.class, id);

    }
}
