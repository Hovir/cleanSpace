package com.godfkc.center.service;

import com.godfkc.center.entity.Card;
import com.godfkc.common.pojo.Ztree;

import java.util.List;

public interface CardService {
    /**
     * @Author:lhy
     * @Data:2018/2/3 12:33
     * @Version:V1.0
     * @Des:添加卡片查询公司信息
     */
    List<Ztree> selectLevel();

    /**
     * @Author:lhy
     * @Data:2018/2/5 10:48
     * @Version:V1.0
     * @Des:生成卡号
     */
    List<Card> insertCard(long id, Integer num);
}
