package com.godfkc.common.pojo.dataTables;

/**
 * @Author:lhy
 * @Data:2018/2/1 13:39
 * @Version:V1.0
 * @Des:处理dataTables请求信息
 */
public class DataTablesReturn {
    //页数
    private Integer page;
    //每页显示的数量
    private Integer size;
    //排序规则
    private String dir;
    //排序字段
    private String data;
    //搜索条件
    private String search;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
