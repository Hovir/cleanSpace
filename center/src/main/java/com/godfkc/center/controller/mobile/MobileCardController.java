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


    //根据cardId查询companyId
    @RequestMapping("/selectCompanyIdByCard/{cardId}")
    public Long selectCompanyIdByCard(@PathVariable("cardId") Long cardId){
        Long companyId=cardService.selectCompanyIdByCard(cardId);
        return companyId;
    }

    //修改userId和status
    @RequestMapping("/updateUserIdAndStatus/{userId}/{cardId}")
    public boolean updateUserIdAndStatus(@PathVariable("userId") Long userId,@PathVariable("cardId") Long cardId){
        int i=cardService.updateUserIdAndStatus(userId,cardId);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }


    @RequestMapping("/selectCardByUserId/{userId}/{status}")
    public Card selectCardByUserId(@PathVariable("userId") Long userId,@PathVariable("status") int status){
        Card card=cardService.selectCardByUserId(userId,status);
        return card;
    }

    @RequestMapping("/updateCardTimes/{cardId}/{times}")
    public boolean updateCardTimes(@PathVariable("cardId") Long cardId,@PathVariable("times") int times){
        int updateRows=cardService.updateCardTimes(cardId,times);
        if (updateRows>0){
            return true;
        }else {
            return false;
        }
    }

    //根据cardId查询卡账号
    @RequestMapping("/selectCardNumById/{cardId}")
    public String selectCardNumById(@PathVariable("cardId") Long cardId){
        String num=cardService.selectCardNumById(cardId);
        return num;
    }

}
