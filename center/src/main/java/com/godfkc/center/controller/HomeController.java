package com.godfkc.center.controller;

import com.godfkc.center.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/home/")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "message", method = {RequestMethod.GET})
    public Map<String, Object> message() {
        return homeService.message();
    }
}
