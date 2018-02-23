package com.godfkc.mobileweb.service.imp;

import com.godfkc.mobileweb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author syx
 * @date 16:58 2018-2-5
 * @description
 */

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class CardServiceImpl implements CardService{

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;


    @Override
    public boolean selectUserCard(Long id) {
        String url = centerUrl + "selectUserCard/{userId}";
        boolean flag = this.restTemplate.getForObject(url, boolean.class, id);
        return flag;
    }

    @Override
    public String checkCard(String num, String password) {
        String url = centerUrl + "checkCard/{num}/{password}";
        Map<String,Object> map = new HashMap<>();
        map.put("num",num);
        map.put("password",password);
        String json = this.restTemplate.getForObject(url, String.class, map);
        return json;
    }


    //根据cardId查询companyId
    @Override
    public Long selectCompanyIdByCard(Long cardId) {
        String url = centerUrl + "selectCompanyIdByCard/{cardId}";
        Long companyId=restTemplate.getForObject(url,Long.class,cardId);
        return companyId;
    }

    //修改userId和status
    @Override
    public boolean updateUserIdAndStatus(Long userId,Long cardId) {
        String url = centerUrl + "updateUserIdAndStatus/{userId}/{cardId}";
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("cardId",cardId);
        boolean flag=restTemplate.getForObject(url,boolean.class,map);
        return flag;
    }

    //根据userId查询卡的剩余次数，卡号
    @Override
    public String selectCardByUserId(Long userId) {
        String url = centerUrl + "selectCardByUserId/{userId}/{status}";
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("status",2);
        String json=restTemplate.getForObject(url,String.class,map);
        return json;
    }

    @Override
    public boolean updateCardTimes(Long cardId, int times) {
        String url = centerUrl + "updateCardTimes/{cardId}/{times}";
        Map<String,Object> map=new HashMap<>();
        map.put("cardId",cardId);
        map.put("times",times);
        boolean flag=restTemplate.getForObject(url,boolean.class,map);
        return flag;
    }
}
