package com.godfkc.center.controller;

import com.godfkc.center.entity.News;
import com.godfkc.center.service.NewsService;
import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.ReturnedData;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.dataTables.ReturnedDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news/")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * @Author:lhy
     * @Data:2018/2/2 11:01
     * @Version:V1.0
     * @Des:查询资讯表格
     */
    @RequestMapping(value = "selectNews", method = {RequestMethod.POST})
    public ReturnedData selectNews(@RequestBody SentParameters sentParameters) {
        //返回到前台的数据
        ReturnedData<News> returnedData = new ReturnedData<>();
        DataTablesReturn dataTablesReturn = new ReturnedDataUtil().DataRequestRequest(sentParameters);

        Page<News> page = newsService.selectNews(1, dataTablesReturn.getPage(), dataTablesReturn.getSize(), dataTablesReturn.getDir(), dataTablesReturn.getData(), dataTablesReturn.getSearch());
        //返回到前台的数据
        returnedData.setDraw(sentParameters.getDraw());
        returnedData.setRecordsTotal((int) page.getTotalElements());
        returnedData.setRecordsFiltered((int) page.getTotalElements());
        returnedData.setData(page.getContent());

        return returnedData;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 11:02
     * @Version:V1.0
     * @Des:添加资讯
     */
    @RequestMapping(value = "insertNews", method = {RequestMethod.POST})
    public void insertNews(@RequestBody News news) {
        newsService.insertNews(news);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 13:00
     * @Version:V1.0
     * @Des:删除新闻
     */
    @RequestMapping(value = "deleteNews", method = {RequestMethod.POST})
    public Boolean deleteNews(@RequestBody List<Long> id) {
        for (Long newsIs : id) {
            newsService.deleteNews(newsIs, 0);
        }
        return true;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:07
     * @Version:V1.0
     * @Des:查询单条新闻
     */
    @RequestMapping(value = "selectNewsById/{id}", method = {RequestMethod.GET})
    public News selectNewsById(@PathVariable("id") Long id) {
        return newsService.selectNewById(id);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:57
     * @Version:V1.0
     * @Des:保存修改的新闻
     */
    @RequestMapping(value = "saveUpdateNewsById", method = {RequestMethod.POST})
    public void saveUpdateNewsById(@RequestBody News news) {
        newsService.saveUpdateNewsById(news);
    }
}
