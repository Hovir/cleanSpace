package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.AddressDict;

import java.util.List;

/**
 * @author syx
 * @date 17:40 2018-2-1
 * @description
 */
public interface AddressDictService {
    List<AddressDict> selectAddressDict(Long parentId);
}
