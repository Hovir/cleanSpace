package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.Order;
import com.godfkc.center.entity.User;
import com.godfkc.center.repository.OrderRepository;
import com.godfkc.center.service.mobile.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public List<Order> findByCompanyIdOrUserId(Integer userId) {
        Long userIdLong = null;
        if (userId != null){
            userIdLong = Long.parseLong(userId.toString());
            List<Order> orderList = this.orderRepository.findByCompanyIdAndUserId(userIdLong);
            return orderList;
        }else {
            return null;
        }
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

    @Override
    public Order saveOrder(Order order) {
        Order save = this.orderRepository.saveAndFlush(order);
        return save;
    }

    @Override
    public List<Order> findAllOrderByCompanyId(Long companyId) {
        return orderRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Order> findAllOrderByCompanyIdAndStatus(Long companyId, Integer status) {
        return orderRepository.findAllByCompanyIdAndStatus(companyId,status);
    }
}
