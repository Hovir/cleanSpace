package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author syx
 * @date 11:10 2018-2-6
 * @description
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/selectNews")
    @ResponseBody
    public String selectNews(){
        String json=newsService.selectNews();
        return json;
    }

    @RequestMapping("/selectNewsById")
    @ResponseBody
    public String selectNewsById(Long newId){
        String json=newsService.selectNewsById(newId);
        return json;
    }

    /**
     *
     * @param: count条目数
     * @return:
     * @author: Qi Zining
     * @date: 2018/2/9
     */
    @RequestMapping("/selectContentAjax/{count}")
    @ResponseBody
    private String selectContent(@PathVariable(value = "count") Integer count){
        return newsService.selectContentByCount(count);
    }

    @RequestMapping("/selectContentByIdAjax")
    @ResponseBody
    public String selectContentById(Long contentId){
        return newsService.selectContentById(contentId);
    }

}
