package com.godfkc.mobileweb.service;



/**
 * @author wj
 * @date 10:18 2018/1/31
 * @description
 */
public interface UserService {
    String findByPhoneAndPassword(String phone, String password);

    String doLogin(String phone, String password);

    Long selectUserIdByPhone(String phone);

    boolean insertUserInfo(String phone, String password);

    boolean updatePwdByPhone(String phone,String password);

    boolean updateUserImgById(String imagePath,String userId);

    boolean updateNameById(String name, Long userId);

}
