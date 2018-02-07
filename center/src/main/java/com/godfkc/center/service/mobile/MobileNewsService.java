package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.News;

import java.util.List;

/**
 * @author syx
 * @date 11:27 2018-2-6
 * @description
 */
public interface MobileNewsService {
    List<News> selectNews();

    News selectNewsById(Long newId);
}
