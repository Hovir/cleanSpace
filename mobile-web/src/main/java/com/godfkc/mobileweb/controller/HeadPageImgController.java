package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.HeadPageImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author syx
 * @date 17:36 2018-2-7
 * @description
 */

@Controller
@RequestMapping("/headPageImg")
public class HeadPageImgController {

    @Autowired
    private HeadPageImgService headPageImgService;


    @ResponseBody
    @RequestMapping("/selectHeadPageImg")
    public String selectHeadPageImg(){
        String json=headPageImgService.selectHeadPageImg();
        return json;
    }
}
