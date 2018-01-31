package com.godfkc.common.pojo.dataTables;

import java.util.HashMap;

/**
 * @User:lhy
 * @Time:2018/1/29 排序规则
 */
public class Order extends HashMap<String, Object> {
    //告诉后台那些列是需要排序的。 i是一个数组索引，对应的是 columns配置的数组，从0开始
    private static final String COLUMN = "column";
    //告诉后台列排序的方式， desc 降序 asc升序
    private static final String DIR = "dir";

    public Order() {
        put(COLUMN, "");
        put(DIR, "");
    }

    public Integer getColumn() {
        return new Integer(get(COLUMN).toString());
    }

    public String getDir() {
        return get(DIR).toString();
    }
}
