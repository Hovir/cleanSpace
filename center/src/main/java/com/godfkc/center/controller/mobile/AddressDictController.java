package com.godfkc.center.controller.mobile;

import com.godfkc.center.entity.AddressDict;
import com.godfkc.center.service.mobile.AddressDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author syx
 * @date 18:15 2018-2-1
 * @description
 */
@RestController
public class AddressDictController {
    @Autowired
    private AddressDictService addressDictService;

    @RequestMapping("/selectAddressDict/{parentId}")
    public List<AddressDict> selectAddressDict(@PathVariable("parentId")Long parentId){
        List<AddressDict> addressDictList = addressDictService.selectAddressDict(parentId);
        return addressDictList;
    }
}
