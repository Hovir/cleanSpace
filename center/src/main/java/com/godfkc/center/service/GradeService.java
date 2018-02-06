package com.godfkc.center.service;

import com.godfkc.center.entity.Level;

import java.util.List;

public interface GradeService {
    /*
    * 通过id查询等级
    * */
    List<Level> findAll();
    /*
    * 修改佣金
    * */
   boolean updataCommision(Long commision,Long id);

    /*
    * 根据id查找数据
    * */
    Level selectById(Long id);
}
