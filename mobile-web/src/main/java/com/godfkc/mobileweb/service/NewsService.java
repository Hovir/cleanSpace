package com.godfkc.mobileweb.service;

/**
 * @author syx
 * @date 11:11 2018-2-6
 * @description
 */
public interface NewsService {
    String selectNews();

    String selectNewsById(Long newId);

    String selectContentByCount(Integer count);

    String selectContentById(Long contentId);
}
