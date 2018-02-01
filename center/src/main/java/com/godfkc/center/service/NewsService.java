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
}
