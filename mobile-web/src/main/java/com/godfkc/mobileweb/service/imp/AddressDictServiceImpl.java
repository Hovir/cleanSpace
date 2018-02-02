package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.AddressDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author syx
 * @date 18:28 2018-2-1
 * @description
 */
@Service
@Transactional
@PropertySource("classpath:config.properties")
public class AddressDictServiceImpl implements AddressDictService{
    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String selectAddressDict(Long parentId) {
        String url = centerUrl + "selectAddressDict/{parentId}";
        System.out.println(url+"-----------------------------------------");
        HashMap<String,Object> map = new HashMap<>(16);
        map.put("parentId",parentId);
        String json = this.restTemplate.getForObject(url, String.class, map);
        System.out.println(json);
        return json;
    }
}
