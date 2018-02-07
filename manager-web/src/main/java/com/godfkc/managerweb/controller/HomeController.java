package com.godfkc.managerweb.controller;

import com.godfkc.managerweb.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/home/")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ResponseBody
    @RequestMapping(value = "message", method = {RequestMethod.GET})
    public Map<String, Object> message() throws Exception {
        Map<String, Object> map = homeService.message();
        //获取系统信息
        Properties properties = System.getProperties();
        InetAddress address = InetAddress.getLocalHost();

        /*Java的运行环境版本*/
        map.put("version", properties.getProperty("java.version"));
        /*Java的运行环境供应商*/
        map.put("vendor", properties.getProperty("java.vendor"));
        /*Java的安装路径*/
        map.put("javaHome", properties.getProperty("java.home"));
        /*系统名称*/
        map.put("osName", properties.getProperty("os.name"));
        /*操作系统的构架*/
        map.put("osArch", properties.getProperty("os.arch"));
        /*系统版本*/
        map.put("osVersion", properties.getProperty("os.version"));
        /*系统ip*/
        map.put("ip", address.getHostAddress());
        /*cpu*/
        map.put("cpu", Runtime.getRuntime().availableProcessors());
        /*虚拟机内存*/
        map.put("ramAll", Runtime.getRuntime().totalMemory());
        /*虚拟机内存空闲量*/
        map.put("ramleLsure", Runtime.getRuntime().freeMemory());
        /*服务器时间*/
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return map;
    }
}
