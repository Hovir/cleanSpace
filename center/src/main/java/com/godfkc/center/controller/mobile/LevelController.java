package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.Level;
import com.godfkc.center.service.mobile.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author syx
 * @date 17:48 2018-2-6
 * @description
 */
@RestController
public class LevelController {

    @Autowired
    private LevelService levelService;

    @RequestMapping("/selectLevels")
    public List<Level> selectLevels(){
        List<Level> levelList=levelService.selectLevels();
        return levelList;
    }

}
