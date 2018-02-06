package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.News;
import com.godfkc.center.service.mobile.MobileNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author syx
 * @date 11:26 2018-2-6
 * @description
 */
@RestController
public class MobileNewsController {

    @Autowired
    private MobileNewsService mobileNewsService;

    @RequestMapping("/selectNews")
    public List<News> selectNews(){
        List<News> newsList= mobileNewsService.selectNews();
        return newsList;
    }
}
