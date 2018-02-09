package com.godfkc.managerweb.service.imp;

import com.godfkc.common.pojo.dataTables.SentParameters;
import com.godfkc.managerweb.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@PropertySource("classpath:config.properties")
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String findWithdrawList(SentParameters sentParameters) {
        String url = centerUrl + "withdraw/withdrawList";
        return this.restTemplate.postForObject(url, sentParameters, String.class);
    }

    @Override
    public void updatePay(Long id) {
        String url = centerUrl + "withdraw/updatePay/{id}";
        String json = restTemplate.getForObject(url, String.class,id);
    }
}
