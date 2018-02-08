package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Order;
import com.godfkc.center.repository.CompanyRepository;
import com.godfkc.center.repository.OrderRepository;
import com.godfkc.center.service.DispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DispatchOrderServiceImpl implements DispatchOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyRepository companyRepository;

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    @Override
    public Page<Order> findAllOrderByType(Integer type, Integer page, Integer size, String dir, String column, String search) {
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, column);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, column);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return orderRepository.findOrdersByType(type,pageable);

    }

    //测试
    @Override
    public List<Order> findOrderByStatus(Integer status) {
        List<Order> ordersByStatus = orderRepository.findAllByStatus(status);
        return ordersByStatus;
    }

    @Override
    public void saveCompany(Long ztreeId, Long id) {
        Company company = companyRepository.findById(ztreeId);
        orderRepository.updateCompanyById(company,id);
    }

    @Override
    public void updateReprot(Order order) {
        Order orderInfo = orderRepository.findOrderById(order.getId());
        orderRepository.updateReportById(order.getReport(),order.getId());
    }


}
