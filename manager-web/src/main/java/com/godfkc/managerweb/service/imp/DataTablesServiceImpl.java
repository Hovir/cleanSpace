package com.godfkc.managerweb.service.imp;

import com.godfkc.managerweb.service.DataTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
@PropertySource("classpath:config.properties")
public class DataTablesServiceImpl implements DataTablesService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${center.url}")
    private String centerUrl;

    @Override
    public String dataTables() {
        String url = centerUrl + "dataTables";
        return this.restTemplate.getForObject(url, String.class);
    }
}
