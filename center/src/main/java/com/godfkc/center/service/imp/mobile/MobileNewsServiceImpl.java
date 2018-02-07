package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.News;
import com.godfkc.center.repository.NewsRepository;
import com.godfkc.center.service.mobile.MobileNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author syx
 * @date 11:28 2018-2-6
 * @description
 */

@Service
@Transactional
public class MobileNewsServiceImpl implements MobileNewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> selectNews() {
        List<News> newsList = newsRepository.findByStatusOrderByCreateTimeDesc(1);
        return newsList;
    }

    @Override
    public News selectNewsById(Long newId) {
        News news = newsRepository.findById(newId);
        return news;
    }
}
