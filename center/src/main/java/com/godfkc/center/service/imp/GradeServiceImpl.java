package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyVo;
import com.godfkc.center.entity.Level;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.repository.LevelRepository;
import com.godfkc.center.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class GradeServiceImpl implements GradeService{



    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private LevelRepository levelRepository;
    /*
    * 修改佣金
    * */
    @Override
    public boolean updataCommision(Long commision,Long id) {
        int b = levelRepository.updataCommision(commision, id);
        if(b>0){
            return true;
        }else{
            return false;
        }

    }
    /*
    * 查找所有等级佣金
    * */
    @Override
    public List<Level> findAll() {
        List<Level> companyVos = levelRepository.findAll();
        return companyVos;
    }

    /*
    * 根据id查找数据
    * */
    @Override
    public Level selectById(Long id) {
        return levelRepository.findOne(id);
    }
}
