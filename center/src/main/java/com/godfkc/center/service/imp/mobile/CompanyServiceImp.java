package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.BankDict;
import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyBankCard;
import com.godfkc.center.repository.BankDictRepository;
import com.godfkc.center.repository.CompanyBankCardRepository;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.service.mobile.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImp implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyBankCardRepository companyBankCardRepository;
    @Autowired
    private BankDictRepository bankDictRepository;
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

    @Override
    public CompanyBankCard findByCompanyId(Long companyId) {
        return companyBankCardRepository.findByCompanyId(companyId);
    }

    @Override
    public int unbindMod(Long bankCardId) {
        return companyBankCardRepository.unbindMod(bankCardId);
    }

    @Override
    public List<BankDict> findBankDictAll(){
        return bankDictRepository.findAll();
    }

    @Override
    public CompanyBankCard bindBankCard(CompanyBankCard companyBankCard) {
        return companyBankCardRepository.save(companyBankCard);
    }

    @Override
    public List<Company> selectCompanies(Long levelId, String state, String city, String district) {
        List<Company> companyList=new ArrayList<>();
        if(levelId==111){
            if(("---省---".equals(state))&&("---市---".equals(city))&&("---区---".equals(district))){
                companyList = companyRepository.findByStatus(1);
            }else if(("---市---".equals(city))&&("---区---".equals(district))){
                companyList=companyRepository.selectCompanyByState(state);
            }else if(("---区---".equals(district))){
                companyList=companyRepository.selectCompanyByStateByCity(state,city);
            }else {
                companyList=companyRepository.selectCompanyByStateByCityByDistrict(state,city,district);
            }
        }else{
            if(("---省---".equals(state))&&("---市---".equals(city))&&("---区---".equals(district))){
                companyList = companyRepository.selectCompanyByLevelId(levelId);
            }else if(("---市---".equals(city))&&("---区---".equals(district))){
                companyList=companyRepository.selectCompanyByLevelIdByState(levelId,state);
            }else if(("---区---".equals(district))){
                companyList=companyRepository.selectCompanyByLevelIdByStateByCity(levelId,state,city);
            }else {
                companyList=companyRepository.selectCompanyByLevelIdByStateByCityByDistrict(levelId,state,city,district);
            }
        }
        return companyList;
    }
}
