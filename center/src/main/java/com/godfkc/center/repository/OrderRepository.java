package com.godfkc.center.repository;

import com.godfkc.center.entity.Company;
import com.godfkc.center.entity.Order;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(" select o from Order o where o.status<>0 and o.user.id = :userId order by o.appointmentTime desc")
    List<Order> findByCompanyIdAndUserId(@Param("userId") Long userId);

    //通过状态（status）查询派遣订单表(order) lqj add 2018-2-1
    //@Query(value = "SELECT cs_order.city,cs_order.name from cs_order WHERE cs_order.status=?1 AND cs_order.id=1 ", nativeQuery = true)
    Page<Order> findOrdersByType(Integer type,Pageable pageable);


    //测试
    List<Order> findAllByStatus(Integer status);


    //通过orderId更改Order表中的检测报告字段（report）lqj add 2018-2-6
    @Modifying
    @Query(value="update Order o SET o.report = ?1,o.status = 2 WHERE o.id = ?2")
      int updateOrderReportById(String report,Long id);
    //int updateOrderReportById(@Param("report") String report,@Param("id") Long id);



    @Query(value = "select o from Order o where o.company.id=:companyId and o.status<>0")
    List<Order> findAllByCompanyId(@Param("companyId") Long companyId);


    //根据id查询预约信息
    @Query(" select o from Order o where o.id = ?1 and o.status<>0")
    Order findOrderById(Long id);

    //查询是否已经添加了报告的订单 lqj add 2018-2-8
    Page<Order> findOrdersByTypeAndStatus(Integer type,Integer status ,Pageable pageable);

    @Modifying
    @Query("update Order o set o.company = ?1 where o.id = ?2")
    void updateCompanyById(Company company, Long id);

    @Query("select o from Order o where o.company.id=?1 and o.status=?2")
    List<Order> findAllByCompanyIdAndStatus(Long companyId,Integer status);

    long countByStatusAfter(Integer status);

    @Modifying
    @Query("update Order o set o.report = ?1,o.status = 2 where o.id = ?2")
    void updateReportById(String imgUrl, Long orderId);
}
