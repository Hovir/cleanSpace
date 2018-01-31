package com.godfkc.center.service;

import com.godfkc.center.entity.News;
import com.godfkc.center.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    /**
     * @Author:lhy
     * @Data:2018/1/31 14:39
     * @Version:V1.0
     * @Des:添加新闻
     */
    void addNews(News news);

    /**
     * @Author:lhy
     * @Data:2018/1/31 15:27
     * @Version:V1.0
     * @Des:查询未删除新闻
     */
    Page<News> selectNews(Integer status, Integer page, Integer size, String dir, String column, String search);
}
