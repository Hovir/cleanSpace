package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.Level;
import com.godfkc.center.repository.LevelRepository;
import com.godfkc.center.service.mobile.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author syx
 * @date 17:50 2018-2-6
 * @description
 */
@Service
@Transactional
public class LevelServiceImpl implements LevelService{

    @Autowired
    private LevelRepository levelRepository;


    @Override
    public List<Level> selectLevels() {
        return levelRepository.findAll();
    }
}
