package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.pojo.manager.CardVo;

import java.util.List;

public interface CardService {
    /**
     * @Author:lhy
     * @Data:2018/2/5 9:45
     * @Version:V1.0
     * @Des:全部公司信息
     */
    String allCompany();

    /**
     * @Author:lhy
     * @Data:2018/2/5 14:11
     * @Version:V1.0
     * @Des:添加卡号
     */
    List<CardVo> insertCard(long id, Integer num);

    /**
     * 查看卡片 zhaozhb
     * @param sentParameters
     * @return
     */
    String findCard(SentParameters sentParameters,Integer status);

}
