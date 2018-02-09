package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Content;
import com.godfkc.center.service.mobile.MobileContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * Author: Qi Zining
 * Date: 2018/2/9
 * Description:
 */
@RestController
public class MobileContentController {
    @Autowired
    private MobileContentService mobileContentService;

    @RequestMapping("/selectNewsByCount/{count}")
    public List<Content> selectNewsByCount(@PathVariable("count") Integer count) {
        Page<Content> contents = mobileContentService.selectByStatus("1", 0, count);
        System.out.println("count"+count);
        return contents.getContent();
    }

    @RequestMapping("/selectContentById/{contentId}")
    public Content selectContentById(@PathVariable("contentId") Long id) {
        return mobileContentService.selectContentById(id);
    }
}
