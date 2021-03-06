package com.godfkc.center.service.imp.admin;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyAddress;
import com.godfkc.center.entity.CompanyFunds;
import com.godfkc.center.entity.Level;
import com.godfkc.center.entity.vo.CompanySearchEmp;
import com.godfkc.center.repository.CompanyAddressRepository;
import com.godfkc.center.repository.CompanyFundsRepository;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.repository.LevelRepository;
import com.godfkc.center.service.admin.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private CompanyAddressRepository companyAddressRepository;

    @Autowired
    private CompanyFundsRepository companyFundsRepository;


    @Override
    public Page<Company> getCompaniesList(Integer page, Integer size, String dir, String data, String search){
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, data);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, data);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return companyRepository.findCompaniesByStatus(1, pageable);
    }

    @Override
    public Page<Company> getCompaniesSearch(CompanySearchEmp companySearchEmp){
        Sort sort = null;
        if(companySearchEmp!=null){
            Integer page=companySearchEmp.getPage();
            Integer size=companySearchEmp.getSize();
            String dir=companySearchEmp.getDir();
            String data= companySearchEmp.getData();
            String search=companySearchEmp.getSearch();
            String companyName=companySearchEmp.getCompanyName();
            Date dateFrom=companySearchEmp.getDateFrom();
            Date dateTo=companySearchEmp.getDateTo();
            //判断排序规则
            if (dir.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, data);
            } else if (dir.equals("asc")) {
                sort = new Sort(Sort.Direction.ASC, data);
            }
            Pageable pageable = new PageRequest(page, size, sort);
            return companyRepository.findCompaniesByCreateTimeBetweenAndNameStartingWithAndStatus(dateFrom,dateTo,companyName,1,pageable);
        }
        return null;
    }

    @Override
    public List<Company> getCompanyIdName() {
        //return companyRepository.findCompanyIdName();
        return companyRepository.findCompanyIdName(1,1L);
    }

    @Override
    public List<Level> getLevelIdName() {
        return levelRepository.findLevelIdName();
    }

    @Override
    public List<Level> getLevesByIdAfter(Long id) {
        return levelRepository.findLevelsByIdAfter(id);
    }

    @Override
    public Company addCompany(Company company) {
        company.setCreateTime(new Date());
        company.setUpdateTime(new Date());
        company.setStatus(1);
        Company companyNew=companyRepository.save(company);
        CompanyFunds companyFunds=null;
        if(companyNew!=null){
            //创建公司账户
            companyFunds=new CompanyFunds();
            companyFunds.setCompany(company);
            companyFunds.setCreateTime(new Date());
            companyFunds.setMoney(0L);
            companyFunds.setStatus(1);
            companyFunds.setUpdateTime(new Date());
            companyFundsRepository.save(companyFunds);
        }
        if(companyFunds!=null){
            return companyNew;
        }
        return null;
    }

    @Override
    public CompanyAddress getCompanyAddress(Company company) {
        return companyAddressRepository.getCompanyAddressByCompany(company);
    }

    @Override
    public Company getCompanyOneDetails(Long id){
        return companyRepository.findCompanyById(id);
    }

    @Override
    public int updateCompanyOneDetails(Long id,String name,String imgUrl,String profile){
       return   companyRepository.updateCompanyById(id,name,imgUrl,profile,new Date());
    }

    @Override
    public int updateCompanyAdress(String state, String city, String district, Company company) {
        return companyAddressRepository.updateCompanyAddressByCompany(state,city,district,company);
    }

    @Override
    public int addCompanyAddress(String state,String city,String district,Company company) {
        CompanyAddress companyAddress=new CompanyAddress();
        companyAddress.setState(state);
        companyAddress.setCity(city);
        companyAddress.setDistrict(district);
        companyAddress.setCompany(company);
        companyAddress=companyAddressRepository.save(companyAddress);
        if (companyAddress!=null){
            return 1;
        }
        return -1;
    }

    @Override
    public int updateCompanyOnePwd(Long id, String password) {
        return companyRepository.updateCompanyPwdById(id,password,new Date());
    }

    @Override
    public int updateCompanyOneStatus(Long id, int status) {
        return companyRepository.updateCompanyStatusById(id,status,new Date());
    }

    @Override
    public Company getCompaniesByBnAndStatus(String bn) {
        return companyRepository.findCompanyByBnAndStatus(bn,1);
    }
}
