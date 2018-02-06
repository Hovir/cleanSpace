package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Card;
import com.godfkc.center.service.mobile.MobileCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author syx
 * @date 17:02 2018-2-5
 * @description
 */
@RestController
public class MobileCardController {

    @Autowired
    private MobileCardService cardService;

    @RequestMapping("/selectUserCard/{userId}")
    public boolean selectUserCard(@PathVariable("userId") Long userId){
        List<Card> cardList=cardService.selectUserCard(userId);
        System.out.println(cardList);
        if(cardList!=null&&cardList.size()>0){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/checkCard/{num}/{password}")
    public Card checkCard(@PathVariable("num") String num,@PathVariable("password") String password){
        Card card=cardService.checkCard(num,password);
        return card;
    }
}