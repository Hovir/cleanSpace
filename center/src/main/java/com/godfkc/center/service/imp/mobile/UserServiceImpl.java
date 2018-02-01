package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.User;
import com.godfkc.center.repository.UserRepository;
import com.godfkc.center.service.mobile.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author syx
 * @date 13:45 2018-2-1
 * @description
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User doLogin(String phone, String password) {
        User user = userRepository.findByPhoneAndPassword(phone, password);
        return user;
    }
}
