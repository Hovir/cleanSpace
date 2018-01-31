package com.godfkc.center.controller;

import com.godfkc.center.entity.News;
import com.godfkc.center.service.NewsService;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news/")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("insertNews")
    public void insertNews(News news) {
        newsService.addNews(news);
    }

    @RequestMapping(value = "selectNews", method = {RequestMethod.POST})
    public ReturnedData selectNews(@RequestBody SentParameters sentParameters) {
        //返回到前台的数据
        ReturnedData<News> returnedData = new ReturnedData<>();
        //计算分页
        Integer index = (sentParameters.getStart() / sentParameters.getLength());
        //每页显示数量
        Integer size = sentParameters.getLength();
        //排序规则
        String dir = sentParameters.getOrder().get(0).getDir();
        //排序字段
        Integer column = sentParameters.getOrder().get(0).getColumn();
        String data = sentParameters.getColumns().get(column).getName();
        //搜索条件
        String search = sentParameters.getSearch().getValue();
        search = search + "%";

        Page<News> page = newsService.selectNews(1, index, size, dir, data, search);

        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());

        return returnedData;
    }
}
