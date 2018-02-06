package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author syx
 * @date 18:20 2018-2-5
 * @description
 */
@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping("/checkCard")
    @ResponseBody
    public Map<String,Object> checkCard(String num,String password){
        Map<String,Object> map=new HashMap<>();
        String json=cardService.checkCard(num,password);
        if(json != null&&json.length()>0){
            map.put("flag","1");
            map.put("card",json);
        }else {
            map.put("flag","2");
        }
        return map;
    }

}
