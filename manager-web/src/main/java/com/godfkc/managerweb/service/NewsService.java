package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

import javax.jws.Oneway;
import java.util.List;
import java.util.Map;


public interface NewsService {

    /**
     * @Author:lhy
     * @Data:2018/1/31 15:45
     * @Version:V1.0
     * @Des:查询新闻
     */
    String select(SentParameters sentParameters);

    /**
     * @Author:lhy
     * @Data:2018/2/2 11:04
     * @Version:V1.0
     * @Des:添加新闻
     */
    void inserNews(Map<String, Object> map);

    /**
     * @Author:lhy
     * @Data:2018/2/2 12:58
     * @Version:V1.0
     * @Des:删除新闻
     */
    Boolean deleteNews(List<Long> id);

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:14
     * @Version:V1.0
     * @Des:查询单条新闻
     */
    String selectNewsById(Long id);

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:58
     * @Version:V1.0
     * @Des:保存修改的新闻
     */
    void saveUpdateNewsById(Map<String, Object> map);
}
