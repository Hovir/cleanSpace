package com.godfkc.center.service.imp;


import com.godfkc.center.entity.User;
import com.godfkc.center.repository.UserRepository;
import com.godfkc.center.service.DataTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DataTablesServiceImpl implements DataTablesService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> dtatTables() {
        return userRepository.findAll();
    }
}
