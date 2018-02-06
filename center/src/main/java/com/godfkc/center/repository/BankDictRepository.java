package com.godfkc.center.repository;

import com.godfkc.center.entity.BankDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther:zhw
 * @Description
 * @Date:Created in 9:12 2018/2/6
 **/
public interface BankDictRepository extends JpaRepository<BankDict,Long> {

}
