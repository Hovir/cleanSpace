package com.godfkc.center.repository;

import com.godfkc.center.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByPhoneAndPasswordAndStatus(String phone,String password,Integer status);

    @Query("select u.id from User u where u.phone=?1 and u.status=1")
    Long selectIdByPhone(String phone);

    @Modifying
    @Query(" update User u set u.password = ?1 where u.phone = ?2 and u.status=1")
    int updatePwdByPhone(String password,String phone);

    @Modifying
    @Query(" update User u set u.headImg = ?1 where u.id = ?2")
    int updateUserImgById(String headImage,Long userId);

    @Modifying
    @Query(" update User u set u.name = ?1 where u.id = ?2")
    int updateNameById(String name,Long userId);
}
