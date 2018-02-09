package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

public interface WithdrawService {

    String findWithdrawList(SentParameters sentParameters);

    void updatePay(Long id);
}
