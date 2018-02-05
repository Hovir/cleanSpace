package com.godfkc.center.repository;

import com.godfkc.center.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LevelRepository extends JpaRepository<Level, Long> {
    /*
    * 修改佣金
    * */
    @Modifying
    @Query(value="update Level l set l.commision=?1 where l.id=?2")
    int updataCommision(Long commision,Long id);

}
