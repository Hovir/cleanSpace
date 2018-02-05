package com.godfkc.center.controller;

import com.godfkc.center.entity.Card;
import com.godfkc.center.entity.Level;
import com.godfkc.center.service.CardService;
import com.godfkc.common.pojo.Ztree;
import com.godfkc.common.pojo.manager.CardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/card/")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * @Author:lhy
     * @Data:2018/2/3 12:39
     * @Version:V1.0
     * @Des:全部等级信息
     */
    @RequestMapping(value = "selectLevel", method = {RequestMethod.POST})
    public List<Ztree> selectLevel() {
        return cardService.selectLevel();
    }

    /**
     * @Author:lhy
     * @Data:2018/2/5 13:37
     * @Version:V1.0
     * @Des:生成卡号和密码
     */
    @RequestMapping(value = "insertCard/{id}/{num}", method = {RequestMethod.GET})
    public List<CardVo> insertCard(@PathVariable("id") Long id, @PathVariable("num") Integer num) {
        List<CardVo> cardVoList = new ArrayList<>();
        List<Card> cardList = cardService.insertCard(id, num);
        for (Card card : cardList) {
            CardVo cardVo = new CardVo();
            cardVo.setId(card.getId());
            cardVo.setNum(card.getNum());
            cardVo.setPassword(card.getPassword());
            cardVo.setCompanyName(card.getCompany().getName());
            cardVo.setStatus(card.getStatus());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cardVo.setCreateTime(simpleDateFormat.format(card.getCreateTime()));
            cardVo.setUpdateTime(simpleDateFormat.format(card.getUpdateTime()));
            cardVoList.add(cardVo);
        }
        return cardVoList;
    }
}
