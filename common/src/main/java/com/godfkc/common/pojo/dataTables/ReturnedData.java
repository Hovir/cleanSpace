package com.godfkc.common.pojo.dataTables;

import java.io.Serializable;
import java.util.List;

/**
 * @User:lhy
 * @Time:2018/1/28 返回dataTables的数据
 */
public class ReturnedData<T> implements Serializable {
    /**
     * 必要。上面提到了，Datatables发送的draw是多少那么服务器就返回多少。
     * 这里注意，作者出于安全的考虑，强烈要求把这个转换为整形，即数字后再返回，
     * 而不是纯粹的接受然后返回，这是 为了防止跨站脚本（XSS）攻击。
     */
    private Integer draw;
    /**
     * 必要。即没有过滤的记录数（数据库里总共记录数）
     */
    private Integer recordsTotal;
    /**
     * 必要。过滤后的记录数
     * （如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
     */
    private Integer recordsFiltered;
    /**
     * 必要。表中中需要显示的数据。这是一个对象数组，也可以只是数组，
     * 区别在于 纯数组前台就不需要用 columns绑定数据，会自动按照顺序去显示 ，
     * 而对象数组则需要使用 columns绑定数据才能正常显示。
     * 注意这个 data的名称可以由 ajaxOption ajax不定时一讲 的 ajax.dataSrcOption ajax.dataSrc 1不定时一讲 ajax.dataSrc 2不定时一讲 控制
     */
    private List<T> data;
    /**
     * 可选。你可以定义一个错误来描述服务器出了问题后的友好提示
     */
    private String error;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
