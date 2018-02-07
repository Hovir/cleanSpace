package com.godfkc.center.repository;

import com.godfkc.center.entity.HeadPageImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HeadPageImgRepository extends JpaRepository<HeadPageImg, Long> {

    List<HeadPageImg> findByStatus(Integer status);

    @Modifying
    @Query(value = "update HeadPageImg set status = :status , updateTime = :updateTime where id = :id")
    void updateHeadImg(@Param("status") Integer status, @Param("updateTime") Date updateTime, @Param("id") long id);

    long countByStatusAfter(Integer status);
}
