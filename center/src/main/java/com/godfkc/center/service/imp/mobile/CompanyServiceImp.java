package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.Company;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.service.mobile.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImp implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Company findByNameAndPassword(String name, String password) {
        Company company = companyRepository.findByNameAndPassword(name,password);
        return company;
    }

    @Override
    public List<Company> selectUnderCompany(Long companyId) {
        List<Company> companyList = companyRepository.selectUnderCompany(companyId);
        return companyList;
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }
}
