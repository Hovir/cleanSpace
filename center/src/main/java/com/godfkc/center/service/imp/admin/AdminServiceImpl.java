package com.godfkc.center.service.imp.admin;

import com.godfkc.center.entity.Admin;
import com.godfkc.center.repository.AdminRepository;
import com.godfkc.center.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin getAdminLogin(String name, String password) {
        return adminRepository.findByNameAndPassword(name,password);
    }

    @Override
    public Admin getAdminLogin(String name, String password,int status) {
        return adminRepository.findByNameAndPasswordAndStatus(name,password,status);
    }
}
