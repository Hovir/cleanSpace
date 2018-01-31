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


@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public void addNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public Page<News> selectNews(Integer status, Integer page, Integer size, String dir, String column, String search) {
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, column);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, column);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return newsRepository.findByStatusAndTitleLike(status, search, pageable);
    }
}
