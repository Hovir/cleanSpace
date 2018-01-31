package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.NewsVo;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/news/")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("addNews")
    public String addNews() {
        NewsVo newsVo = new NewsVo();
        newsVo.setTitle("测试");
        newsVo.setDetails("测试");
        newsVo.setStatus(1);
        newsVo.setCreateTime(new Date());
        newsVo.setUpdateTime(new Date());
        newsService.addNews(newsVo);
        return "";
    }

    @ResponseBody
    @RequestMapping("selectNews")
    public String selectNews(SentParameters sentParameters) {
        return newsService.select(sentParameters);
    }
}
