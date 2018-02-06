package com.godfkc.mobileweb.service;

/**
 * @author syx
 * @date 16:52 2018-2-5
 * @description
 */
public interface CardService {
    boolean selectUserCard(Long id);

    String checkCard(String num, String password);

}
