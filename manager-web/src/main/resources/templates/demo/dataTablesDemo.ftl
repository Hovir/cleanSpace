<#--
  Created by IntelliJ IDEA.
  com.godfkc.pojo.User: 刘洪宇
  Date: 2018/1/3
  Time: 17:46
  To change this templates use File | Settings | File Templates.
-->
<#--获取绝对路径-->
<#assign path=springMacroRequestContext.getContextPath() />
<nav class="breadcrumb"><i class="Hui-iconfont"></i>
    <a href="javascript:;" onclick="pageTurns('manager/_home')" class="maincolor">首页</a>
    <span class="c-999 en">&gt;</span><span class="c-666">dataTablesDemo</span></nav>
<div class="Hui-article">
    <article class="cl pd-20">

        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr class="text-c">
                    <th width="25"><input type="checkbox"></th>
                    <th width="80">ID</th>
                    <th>标题</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

    <#--请在下方写此页面业务相关的脚本-->
        <script type="application/javascript" src="${path}/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
        <script type="application/javascript" src="${path}/lib/js/dataTables.js"></script>
        <script type="application/javascript">
            var url = "${path}/demo/dataTables";
            /*列对应表*/
            var columns = [
                {data: null},
                {data: "id"},
                {data: "name"}
            ];
            /*特殊列渲染*/
            var columnDefs = [
                {
                    //表示第0列
                    targets: 0,
                    //渲染函数
                    render: function (data) {
                        return "<td><input type=\"checkbox\" name=\"cb\" value=\"" + data.id + "\"/></td>";
                    }
                }, {
                    //禁用排序
                    orderable: false,
                    //指定的列
                    targets: [0]
                }
            ];
            /*初始化dataTables*/
            dataTables(url, columns, columnDefs);
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
</div>