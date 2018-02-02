package com.godfkc.center.service;

import com.godfkc.center.entity.News;
import com.godfkc.center.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    /**
     * @param status:状态;
     * @param page:当前页数;
     * @param size:每页显示的数量;
     * @param dir:排序规则;
     * @param data:排序字段;
     * @param search:搜索条件;
     * @Author:lhy
     * @Data:2018/1/31 15:27
     * @Version:V1.0
     * @Des:查询未删除新闻
     */
    Page<News> selectNews(Integer status, Integer page, Integer size, String dir, String data, String search);

    /**
     * @Author:lhy
     * @Data:2018/2/2 11:00
     * @Version:V1.0
     * @Des:添加资讯
     */
    void insertNews(News news);
    /**
     * @Author:lhy
     * @Data:2018/2/2 13:02
     * @Version:V1.0
     * @Des:删除新闻
     */
    void deleteNews(Long id , int status);
    /**
     * @Author:lhy
     * @Data:2018/2/2 14:04
     * @Version:V1.0
     * @Des:查询单条新闻数据
     */
    News selectNewById(long id);
    /**
     * @Author:lhy
     * @Data:2018/2/2 14:55
     * @Version:V1.0
     * @Des:保存修改的新闻
     */
    void saveUpdateNewsById(News news);
}
