package com.godfkc.managerweb.service;

import java.util.List;
import java.util.Map;

public interface GradeService {
    /*
    * 根据id查询分级
    * */
    String findAll();


    /*
    *根据id查询数据
    * */
    Map<String,Object> selectById(Long id);

    /*
    * 修改佣金
    * */
    boolean updataCommision(Long commision,Long id);
}
