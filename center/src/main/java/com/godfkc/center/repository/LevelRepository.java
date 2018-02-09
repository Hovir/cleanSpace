package com.godfkc.center.repository;

import com.godfkc.center.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
    /*
    * 修改佣金
    * */
    @Modifying
    @Query(value="update Level l set l.commision=?1 where l.id=?2")
    int updataCommision(Long commision,Long id);




    /**
     * 后台-企业列表
     * 后台-company-添加数据下拉列表查询
     * @return
     */
    @Query("select id,name from Level ")
    List<Level> findLevelIdName();

    /**
     * 后台-企业列表
     * 后台-company-添加数据下拉列表查询-级联
     * @return
     */
    List<Level> findLevelsByIdAfter(long id);
}
