package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.Order;
import com.godfkc.center.entity.User;
import com.godfkc.center.repository.OrderRepository;
import com.godfkc.center.service.mobile.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 19:44 2018/2/1
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findByCompanyIdOrUserId(String companyId, String userId) {
        Long companyIdLong = null;
        Long userIdLong = null;
        if(companyId!=null){
            companyIdLong = Long.parseLong(companyId);
        }
        if (userId != null){
            userIdLong = Long.parseLong(userId);
        }
        if (companyId!=null && userId!=null){
            List<Order> orderList1 = this.orderRepository.findByCompanyIdAndUserId(companyIdLong, userIdLong);
            return orderList1;
        }
        List<Order> orderList = this.orderRepository.findByCompanyIdOrUserId(companyIdLong, userIdLong);
        return orderList;
    }

    @Override
    public Order addOrder(Order order) {
        Order order1 = orderRepository.save(order);
        return order1;
    }

    @Override
    public Order findOrderById(Long id) {
        return this.orderRepository.findOrderById(id);
    }
}
