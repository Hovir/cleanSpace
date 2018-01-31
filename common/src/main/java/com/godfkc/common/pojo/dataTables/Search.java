package com.godfkc.common.pojo.dataTables;

import java.util.HashMap;
/**
* @User:lhy
* @Time:2018/1/29
* 搜索条件
*/
public class Search extends HashMap<String, Object> {
    //标记具体列的搜索条件
    private static final String VALUE = "value";
    //特定列的搜索条件是否视为正则表达式， 如果为 true代表搜索的值是作为正则表达式处理，为 false则不是。
    // 注意：通常在服务器模式下对于大数据不执行这样的正则表达式，但这都是自己决定的
    private static final String REGEX = "regex";

    public Search() {
        put(VALUE, "");
        put(REGEX, "");
    }

    public String getValue() {
        return get(VALUE).toString();
    }

    public Boolean getRegex() {
        return (Boolean) get(REGEX);
    }
}
