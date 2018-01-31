package com.godfkc.mobileweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/31
 * @description
 */
@Controller
public class DemoController {

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String demo() {

        return "demo";
    }
}
