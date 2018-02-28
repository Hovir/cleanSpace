package com.godfkc.center.repository;

import com.godfkc.center.entity.Card;
import com.godfkc.center.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    Card checkCard(String num, String password);

    long countByStatusAfter(Integer status);

    @Query("select c.company.id from Card c where c.id=?1")
    Long selectCompanyIdByCard(Long cardId);

    @Modifying
    @Query("update Card c set c.user.id=?1,c.status=2 where c.id=?2")
    int updateUserIdAndStatus(Long userId,Long cardId);


    @Query("select c from Card c where c.user.id=?1 and c.status=?2")
    Card selectCardByUserId(Long userId, int status);

    //修改card使用次数
    @Modifying
    @Query("update Card c set c.times=?1 where c.id=?2")
    int updateCardTimes(int times,Long cardId);

    //根据cardId查询卡账号
    @Query("select c.num from Card c where c.id=?1")
    String selectCardNumById(Long cardId);
}
