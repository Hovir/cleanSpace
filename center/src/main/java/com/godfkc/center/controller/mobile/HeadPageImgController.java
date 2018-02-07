package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.HeadPageImg;
import com.godfkc.center.service.mobile.HeadPageImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author syx
 * @date 17:46 2018-2-7
 * @description
 */
@RestController
public class HeadPageImgController {

    @Autowired
    private HeadPageImgService headPageImgService;

    @RequestMapping("/selectHeadPageImg")
    public List<HeadPageImg> selectHeadPageImg(){
        List<HeadPageImg> headPageImgList=headPageImgService.selectHeadPageImg();
        return headPageImgList;
    }

}
