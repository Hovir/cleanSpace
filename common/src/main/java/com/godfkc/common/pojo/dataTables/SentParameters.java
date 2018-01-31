package com.godfkc.common.pojo.dataTables;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @User:lhy
 * @Time:2018/1/28 接收dataTable发送来的数据
 */
public class SentParameters implements Serializable {
    /**
     * 绘制计数器。这个是用来确保Ajax从服务器返回的是对应的
     * （Ajax是异步的，因此返回的顺序是不确定的）。 要求在服务器接收到此参数后再返回
     */
    private Integer draw;
    /**
     * 第一条数据的起始位置，比如0代表第一条数据
     */
    private Integer start;
    /**
     * 告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，
     * 可能会大于因为服务器可能没有那么多数据。这个也可能是-1，代表需要返回全部数据(尽管这个和服务器处理的理念有点违背)
     */
    private Integer length;
    /**
     * value(String):全局的搜索条件，条件会应用到每一列（ searchable需要设置为 true ）
     * regex(boolean):如果为 true代表全局搜索的值是作为正则表达式处理，为 false则不是。
     * 注意：通常在服务器模式下对于大数据不执行这样的正则表达式，但这都是自己决定的
     */
    private Search search;
    /**
     * column(integer):告诉后台那些列是需要排序的。
     * dir(string):告诉后台列排序的方式， desc 降序 asc升序
     */
    private List<Order> order;
    /**
     * 每列的信息
     */
    private List<Columns> columns;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }
}
