package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.AddressDict;
import com.godfkc.center.repository.AddressDictRepository;
import com.godfkc.center.service.mobile.AddressDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author syx
 * @date 18:10 2018-2-1
 * @description
 */
@Service
@Transactional
public class AddressDictServiceImpl implements AddressDictService{
    @Autowired
    private AddressDictRepository addressDictRepository;


    @Override
    public List<AddressDict> selectAddressDict(Long parentId) {
        List<AddressDict> addressDictList=new ArrayList<>();
        if(parentId==1){
            addressDictList = addressDictRepository.selectAddressDictIsNull();
        }else {
            addressDictList = addressDictRepository.selectAddressDictList(parentId);
        }
        return addressDictList;
    }
}

