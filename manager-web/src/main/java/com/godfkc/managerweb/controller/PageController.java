package com.godfkc.managerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:lhy
 * @Data:2018/1/30 17:41
 * @Version:V1.0
 * @Des:页面跳转
 */
@Controller
public class PageController {
    /*ajax异步刷新页面*/
    @RequestMapping(value = "/page/{cat}/{page}", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public String page(@PathVariable String cat, @PathVariable String page) {
        return "/" + cat + "/" + page;
    }

    /*首页*/
    @RequestMapping(value = "/index")
    public String index() {
        return "/manager/_index";
    }

    /*test page*/
    @RequestMapping(value = "/testPage")
    public String testPage(){
        return "/manager/_test";
    }
}
