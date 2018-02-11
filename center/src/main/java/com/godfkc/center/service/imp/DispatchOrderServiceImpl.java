package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.CompanyFunds;
import com.godfkc.center.entity.Order;
import com.godfkc.center.repository.CompanyFundsRepository;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DispatchOrderServiceImpl implements DispatchOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyFundsRepository companyFundsRepository;

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

    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    @Override
    public boolean updateOrderReport( String report,Long id) {
        int b = orderRepository.updateOrderReportById(report, id);
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbb"+b);
        if(b>0){
            return true;
        }else {
            return false;
        }

    }

    //测试
    @Override
    public List<Order> findOrderByStatus(Integer status) {
        List<Order> ordersByStatus = orderRepository.findAllByStatus(status);
        return ordersByStatus;
    }

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    @Override
    public Page<Order> findAllOrderByTypeAndStatus(Integer type,Integer status, Integer page, Integer size, String dir, String column, String search) {
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, column);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, column);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return orderRepository.findOrdersByTypeAndStatus(type,status,pageable);

    }

    //添加Order表中companyId字段 zzb add
    @Override
    public void saveCompany(Long ztreeId, Long id) {
        Order order = orderRepository.findOrderById(id);
        Company company = companyRepository.findById(ztreeId);
        if(order.getCompany()==null){
            orderRepository.updateCompanyById(company,id);
            //通过companyId查询funds表中是否有该公司信息，如果有就更新money字段，如果没有则新添加一条数据 lqj add 2018-2-8
            CompanyFunds companyFundsByCompanyId = companyFundsRepository.findCompanyFundsByCompanyId(ztreeId);
            if(companyFundsByCompanyId == null){
                CompanyFunds companyFunds = new CompanyFunds();
                companyFunds.setCreateTime(new Date());
                companyFunds.setMoney(company.getLevel().getCommision());
                companyFunds.setStatus(1);
                companyFunds.setCompany(company);
                companyFunds.setUpdateTime(new Date());
                companyFundsRepository.save(companyFunds);
            }else{
                companyFundsRepository.updateFundsMoneyAndUpdateTimeByCompanyId(company.getLevel().getCommision(),new Date(),ztreeId);
            }
        }

    }

    @Override
    public void updateReprot(Order order) {
        Order orderInfo = orderRepository.findOrderById(order.getId());
        orderRepository.updateReportById(order.getReport(),order.getId());
    }


}
