package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.User;

/**
 * @author syx
 * @date 13:40 2018-2-1
 * @description
 */

public interface UserService {
    User doLogin(String phone,String password);

    Long selectUserIdByPhone(String phone);

    User saveUserInfo(User user);

    int updatePwdByPhone(String password,String phone);

    int updateUserImgById(String headImage,Long userId);

    int updateNameById(String name,Long userId);
}
