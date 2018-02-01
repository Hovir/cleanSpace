package com.godfkc.common.utils.dataTables;

import com.godfkc.common.pojo.dataTables.DataTablesReturn;
import com.godfkc.common.pojo.dataTables.SentParameters;
/**
 * @Author:lhy
 * @Data:2018/2/1 13:49
 * @Version:V1.0
 * @Des:处理dataTables返回到后台的数据
 */
public class ReturnedDataUtil {
    public DataTablesReturn DataRequestRequest(SentParameters sentParameters) {
        DataTablesReturn dataTablesReturn = new DataTablesReturn();

        //计算分页
        Integer page = (sentParameters.getStart() / sentParameters.getLength());
        //每页显示数量
        Integer size = sentParameters.getLength();
        //排序规则
        String dir = sentParameters.getOrder().get(0).getDir();
        //排序字段
        Integer column = sentParameters.getOrder().get(0).getColumn();
        String data = sentParameters.getColumns().get(column).getName();
        //搜索条件
        String search = sentParameters.getSearch().getValue();
        search = search + "%";

        dataTablesReturn.setPage(page);
        dataTablesReturn.setSize(size);
        dataTablesReturn.setData(data);
        dataTablesReturn.setDir(dir);
        dataTablesReturn.setSearch(search);
        return dataTablesReturn;
    }
}
