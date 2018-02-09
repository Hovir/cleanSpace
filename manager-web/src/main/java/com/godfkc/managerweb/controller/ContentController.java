package com.godfkc.managerweb.controller;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/content/")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * @Author:lhy
     * @Data:2018/2/1 15:09
     * @Version:V1.0
     * @Des:跳转到添加资讯页面
     */
    @RequestMapping("openContentAdd")
    public String addNews() {
        return "content/contentAdd";
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 15:08
     * @Version:V1.0
     * @Des:资讯列表
     */
    @ResponseBody
    @RequestMapping("selectList")
    public String selectNews(SentParameters sentParameters) {
        return contentService.contentLis(sentParameters);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/9 14:51
     * @Version:V1.0
     * @Des:删除
     */
    @ResponseBody
    @RequestMapping("contentDelete")
    public Boolean contentDelete(@RequestParam("id[]") List<Long> id) {
        return contentService.delect(id);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/1 20:54
     * @Version:V1.0
     * @Des:添加资讯
     */
    @RequestMapping(value = "addContent", method = {RequestMethod.POST})
    public String addNews(@RequestParam("title") String title, @RequestParam("imgUrl") String imgUrl, @RequestParam("content") String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("imgUrl", imgUrl);
        map.put("status", 1);
        contentService.insertContent(map);
        return "redirect:/content/openContentAdd";
    }

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:09
     * @Version:V1.0
     * @Des:图片上传
     */
    @ResponseBody
    @RequestMapping("uploader")
    public Map<String, Object> uploader(@RequestParam("file") MultipartFile[] file) {
        NewsController newsController = new NewsController();
        return newsController.uploader(file);
    }

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:30
     * @Version:V1.0
     * @Des:修改资讯
     */
    @RequestMapping("openUpdateContent/{id}")
    public ModelAndView openUpdateContent(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("content/contentUpade");
        modelAndView.addObject("json", contentService.openUpdateContent(id));
        return modelAndView;
    }

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:48
     * @Version:V1.0
     * @Des:保存修改的资讯
     */
    @RequestMapping("saveUpdateContent")
    public String saveUpdateContent(@RequestParam("id") long id, @RequestParam("title") String title, @RequestParam("imgUrl") String imgUrl, @RequestParam("content") String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("content", content);
        map.put("imgUrl", imgUrl);
        map.put("status", 1);
        this.contentService.saveUpdateContent(map);
        return "redirect:/content/openUpdateContent/" + id;
    }
}
