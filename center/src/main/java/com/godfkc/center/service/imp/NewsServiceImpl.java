package com.godfkc.center.service.imp;

import com.godfkc.center.entity.News;
import com.godfkc.center.repository.NewsRepository;
import com.godfkc.center.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Page<News> selectNews(Integer status, Integer page, Integer size, String dir, String data, String search) {
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, data);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, data);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return newsRepository.findByStatusAndTitleLike(status, search, pageable);
    }

    @Override
    public void insertNews(News news) {
        news.setCreateTime(new Date());
        news.setUpdateTime(new Date());
        newsRepository.save(news);
    }

    @Override
    public void deleteNews(Long id, int status) {
        newsRepository.updateNewsById(id, status);
    }

    @Override
    public News selectNewById(long id) {
        return newsRepository.findNewsById(id);
    }

    @Override
    public void saveUpdateNewsById(News news) {
        newsRepository.saveUpdateNewsById(news.getTitle(), news.getImg_url(), news.getDetails(), new Date(), news.getId());
    }
}
