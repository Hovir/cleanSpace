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
        User user = userRepository.findByPhoneAndPasswordAndStatus(phone, password,1);
        return user;
    }

    @Override
    public Long selectUserIdByPhone(String phone) {
        Long id = userRepository.selectIdByPhone(phone);
        return id;
    }

    @Override
    public User saveUserInfo(User user) {
        return userRepository.save(user);
    }

    @Override
    public int updatePwdByPhone(String phone, String password) {
        return userRepository.updatePwdByPhone(password,phone);
    }

    @Override
    public int updateUserImgById(String headImage,Long userId) {
        return userRepository.updateUserImgById(headImage,userId);
    }

    @Override
    public int updateNameById(String name, Long userId) {
        return userRepository.updateNameById(name,userId);
    }
}
