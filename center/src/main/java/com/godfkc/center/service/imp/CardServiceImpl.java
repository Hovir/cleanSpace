package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Level;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.repository.LevelRepository;
import com.godfkc.center.service.CardService;
import com.godfkc.common.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Ztree> selectLevel() {
        List<Ztree> ztreeList = new ArrayList<>();
        List<Level> levelList = levelRepository.findAll();
        List<Company> companyList = companyRepository.findAll();

        for (Level level : levelList) {
            Ztree ztree = new Ztree();
            ztree.setId(level.getId());
            ztree.setpId(0);
            ztree.setName(level.getName());
            ztree.setNocheck(true);
            ztreeList.add(ztree);
        }
        for (Company company : companyList) {
            Ztree ztree = new Ztree();
            ztree.setId(company.getId());
            ztree.setpId(company.getLevel().getId());
            ztree.setName(company.getName());
            ztreeList.add(ztree);
        }
        return ztreeList;
    }
}
