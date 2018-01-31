package com.godfkc.center.repository;

import com.godfkc.center.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findByStatusAndTitleLike(Integer status, String title, Pageable pageable);

}
