package com.godfkc.mobileweb.service;

/**
 * @author syx
 * @date 17:41 2018-2-6
 * @description
 */
public interface LevelService {
    String selectLevels();

    //根据levelId查询佣金
    Long selectCommisionByLevelId(Long levelId);
}
