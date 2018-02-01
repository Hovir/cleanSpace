package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/news/")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /**
     * @Author:lhy
     * @Data:2018/2/1 15:08
     * @Version:V1.0
     * @Des:资讯列表
     */
    @ResponseBody
    @RequestMapping("selectNews")
    public String selectNews(SentParameters sentParameters) {
        return newsService.select(sentParameters);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 15:09
     * @Version:V1.0
     * @Des:跳转到添加资讯页面
     */
    @RequestMapping("openAddNews")
    public String addNews() {
        return "advisory/addAdvisory";
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 18:49
     * @Version:V1.0
     * @Des:图片上传
     */
    @RequestMapping("uploader")
    public Map<String, Object> uploader(@RequestParam("files") MultipartFile[] files) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(files.length);
        return map;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 20:54
     * @Version:V1.0
     * @Des:添加资讯
     */
    @RequestMapping(value = "addNews", method = {RequestMethod.POST})
    public String addNews(@RequestParam("title") String title, @RequestParam("img") MultipartFile img, @RequestParam("details") String detailsx) {
        return "";
    }
}
