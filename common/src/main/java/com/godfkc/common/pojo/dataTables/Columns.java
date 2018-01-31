package com.godfkc.common.pojo.dataTables;

import java.util.HashMap;

/**
 * @User:lhy
 * @Time:2018/1/29 每一个列的具体信息
 */
public class Columns extends HashMap<String, Object> {
    //columns 绑定的数据源，由 columns.dataOption 定义。
    private static final String DATA = "data";
    //columns 的名字，由 columns.nameOption 定义。
    private static final String NAME = "name";
    //标记列是否能被搜索,为true代表可以，否则不可以，这个是由 columns.searchableOption 控制
    private static final String SEARCHABLE = "searchable";
    //标记列是否能排序,为 true代表可以，否则不可以，这个是由 columns.orderableOption 控制
    private static final String ORDERABLE = "orderable";
    //每列搜索条件
    private static final String SEARCH = "search";

    public Columns() {
        put(DATA, "");
        put(NAME, "");
        put(SEARCHABLE, "");
        put(ORDERABLE, "");
        put(SEARCH, new Search());
    }

    public String getData() {
        return get(DATA).toString();
    }

    public String getName() {
        return get(NAME).toString();
    }

    public Boolean getSearchable() {
        return (Boolean) get(SEARCHABLE);
    }

    public Boolean getOrderable() {
        return (Boolean) get(ORDERABLE);
    }

    public Search getSearch() {
        return (Search) get(SEARCH);
    }
}
