package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.NewsVo;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;


public interface NewsService {
    /**
     * @Author:lhy
     * @Data:2018/1/31 15:45
     * @Version:V1.0
     * @Des:添加新闻
     */
    void addNews(NewsVo newsVo);

    /**
     * @Author:lhy
     * @Data:2018/1/31 15:45
     * @Version:V1.0
     * @Des:查询新闻
     */
    String select(SentParameters sentParameters);
}
