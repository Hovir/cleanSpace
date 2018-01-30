package com.godfkc.managerweb.service.imp;

import com.godfkc.managerweb.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
@Service
@Transactional
public class DemoServiceImp implements DemoService {
    @Autowired
    private RestTemplate restTemplate;

}
