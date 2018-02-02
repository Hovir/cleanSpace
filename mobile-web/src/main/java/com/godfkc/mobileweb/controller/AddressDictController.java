package com.godfkc.mobileweb.controller;

import com.godfkc.mobileweb.service.AddressDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author syx
 * @date 18:32 2018-2-1
 * @description
 */
@Controller
@RequestMapping("/addressDict")
public class AddressDictController {
    @Autowired
    private AddressDictService addressDictService;

    @ResponseBody
    @RequestMapping("/selectAddressDict")
    public String selectAddressDict(Long parentId){
        String json = addressDictService.selectAddressDict(parentId);
        return json;
    }

}
