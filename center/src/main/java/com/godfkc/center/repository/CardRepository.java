package com.godfkc.center.repository;

import com.godfkc.center.entity.Card;
import com.godfkc.center.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Card> findByStatusAndNumLike(Integer status, String search, Pageable pageable);


    @Query("select c from Card c where c.user.id=?1 and c.status=2")
    List<Card> selectUserCard(Long userId);

    @Query("select c from Card c where c.num=?1 and c.password=?2 and c.status=1")
    Card checkCard(String num,String password);
}
