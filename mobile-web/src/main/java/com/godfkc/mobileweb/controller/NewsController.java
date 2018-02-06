package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
