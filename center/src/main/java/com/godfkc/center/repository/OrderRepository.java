package com.godfkc.center.repository;

import com.godfkc.center.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(" select o from Order o where o.company.id = :companyId and o.status<>0 or o.user.id = :userId and o.status<>0")
    List<Order> findByCompanyIdOrUserId(@Param("companyId") Long companyId,@Param("userId") Long userId);

    @Query(" select o from Order o where o.status<>0 and o.company.id = :companyId and o.user.id = :userId")
    List<Order> findByCompanyIdAndUserId(@Param("companyId") Long companyId,@Param("userId") Long userId);

    //根据id查询预约信息
    @Query(" select o from Order o where o.id = ?1 and o.status<>0")
    Order findOrderById(Long id);
}
