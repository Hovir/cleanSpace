package com.godfkc.center.controller;

import com.godfkc.center.entity.Level;
import com.godfkc.center.service.CardService;
import com.godfkc.common.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "selectLevel")
    public List<Ztree> selectLevel() {
        return cardService.selectLevel();
    }
}
