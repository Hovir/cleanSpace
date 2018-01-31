package com.godfkc.managerweb.controller.demo;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.DataTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class DataTablesController {

    @Autowired
    private DataTablesService dataTablesService;

    @ResponseBody
    @RequestMapping("dataTables")
    public String dataTables() {
        return dataTablesService.dataTables();
    }
}
