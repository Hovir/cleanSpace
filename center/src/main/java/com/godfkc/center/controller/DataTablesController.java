package com.godfkc.center.controller;

import com.godfkc.center.entity.User;
import com.godfkc.center.service.DataTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataTablesController {

    @Autowired
    private DataTablesService dataTablesService;

    @RequestMapping("dataTables")
    public List<User> dataTables() {
        return dataTablesService.dtatTables();
    }

}
