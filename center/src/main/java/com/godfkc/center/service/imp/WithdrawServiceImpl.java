package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyFundsWithdraw;
import com.godfkc.center.repository.CompanyFundsWithdrawRepository;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private CompanyFundsWithdrawRepository companyFundsWithdrawRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Page<CompanyFundsWithdraw> getWithdrawList(Integer page, Integer size, String dir, String data, String search) {
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, data);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, data);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return companyFundsWithdrawRepository.findWithdrawListByStatus(1, pageable);
    }

    @Override
    public void updatePay(Long id) {
        companyFundsWithdrawRepository.updateIsPayById(id);
    }
}
