package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Card;

import java.util.List;

/**
 * @author syx
 * @date 17:04 2018-2-5
 * @description
 */
public interface MobileCardService {

    List<Card> selectUserCard(Long userId);

    Card checkCard(String num, String password);
}