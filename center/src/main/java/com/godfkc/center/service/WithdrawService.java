package com.godfkc.center.service;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyFundsWithdraw;
import org.springframework.data.domain.Page;

public interface WithdrawService {

    Page<CompanyFundsWithdraw> getWithdrawList(Integer page, Integer size, String dir, String data, String search);

    void updatePay(Long id);
}
