package com.godfkc.managerweb.controller;

import com.godfkc.common.constant.PictureServerConstants;
import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.common.utils.FastDFSClient;
import com.godfkc.managerweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

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
     * @Data:2018/2/2 13:58
     * @Version:V1.0
     * @Des:跳转新闻修改页面
     */
    @RequestMapping("updateNews/{id}")
    public ModelAndView updateNews(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("advisory/updateAdvisory");
        modelAndView.addObject("json", newsService.selectNewsById(id));
        return modelAndView;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:48
     * @Version:V1.0
     * @Des:保存修改的新闻
     */
    @RequestMapping("saveUpdateNews")
    public String saveUpdateNews(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("imgUrl") String imgUrl, @RequestParam("details") String details) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("details", details);
        map.put("img_url", imgUrl);
        map.put("status", 1);
        newsService.saveUpdateNewsById(map);
        return "redirect:/news/updateNews/" + id;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 18:49
     * @Version:V1.0
     * @Des:图片上传
     */
    @ResponseBody
    @RequestMapping("uploader")
    public Map<String, Object> uploader(@RequestParam("file") MultipartFile[] file) {
        Map<String, Object> map = new HashMap<>();
        List<String> list = fileUpload(file);
        map.put("errno", 0);
        map.put("data", list);
        return map;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 20:54
     * @Version:V1.0
     * @Des:添加资讯
     */
    @RequestMapping(value = "addNews", method = {RequestMethod.POST})
    public String addNews(@RequestParam("title") String title, @RequestParam("imgUrl") String imgUrl, @RequestParam("details") String details) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("details", details);
        map.put("img_url", imgUrl);
        map.put("status", 1);
        newsService.inserNews(map);
        return "redirect:/news/openAddNews";
    }


    /**
     * @Author:lhy
     * @Data:2018/2/2 11:44
     * @Version:V1.0
     * @Des:删除新闻
     */
    @ResponseBody
    @RequestMapping("deleteNews")
    public Boolean deleteNews(@RequestParam("id[]") List<Long> id) {
        return newsService.deleteNews(id);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/2 9:43
     * @Version:V1.0
     * @Des:图片上传
     */
    public List<String> fileUpload(MultipartFile[] files) {
        List<String> test = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                //获取上传文件的名字
                String fileName = file.getOriginalFilename();
                //获取上传文件的后缀名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                //对后缀名大小写进行转换
                fileExt = fileExt.toLowerCase();
                //创建fastclien
                FastDFSClient fastDFSClient = new FastDFSClient("fastDFS.properties");
                //上传到fastDfs
                String urlName = fastDFSClient.uploadFile(file.getBytes(), fileExt);
                test.add(PictureServerConstants._PICTURE_SERVER_URL + urlName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}
