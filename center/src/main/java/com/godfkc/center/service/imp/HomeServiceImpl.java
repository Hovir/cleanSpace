package com.godfkc.center.service.imp;

import com.godfkc.center.repository.*;
import com.godfkc.center.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private HeadPageImgRepository headPageImgRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CardRepository cardRepository;

    public Map<String, Object> message() {
        Map<String, Object> map = new HashMap<>();

        map.put("news", newsRepository.countByStatusAfter(0));
        map.put("company", companyRepository.countByStatusAfter(0));
        map.put("headPageImg", headPageImgRepository.countByStatusAfter(0));
        map.put("order", orderRepository.countByStatusAfter(0));
        map.put("card", cardRepository.countByStatusAfter(0));

        return map;
    }

}
