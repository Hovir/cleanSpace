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
}
