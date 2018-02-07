package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author syx
 * @date 17:39 2018-2-6
 * @description
 */
@Controller
@RequestMapping("/level")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @RequestMapping("/selectLevels")
    @ResponseBody
    public String selectLevels(){
        String json=levelService.selectLevels();
        return json;
    }
}
