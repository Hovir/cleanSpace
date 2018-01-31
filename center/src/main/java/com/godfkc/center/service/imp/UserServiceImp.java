package com.godfkc.center.service.imp;

import com.godfkc.center.entity.User;
import com.godfkc.center.repository.UserRepository;
import com.godfkc.center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wj
 * @date 10:09 2018/1/31
 * @description 用户相关
 */
@Service
@Transactional
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByPhoneAndPassword(String phone, String password) {
        return userRepository.findByPhoneAndPassword(phone,password);
    }
}
