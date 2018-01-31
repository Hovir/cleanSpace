<#--
  Created by IntelliJ IDEA.
  User: 刘洪宇
  Date: 2018/1/19
  Time: 12:08
  To change this template use File | Settings | File Templates.
-->
<#assign path=springMacroRequestContext.getContextPath() />
<nav class="breadcrumb"><i class="Hui-iconfont"></i>
    <a href="javascript:;" onclick="pageTurns('manager/_home')" class="maincolor">首页</a>
    <span class="c-999 en">&gt;</span><span class="c-666">资讯管理</span></nav>
<div class="Hui-article">
    <article class="cl pd-20">

        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr class="text-c">
                    <th width="25"><input type="checkbox"></th>
                    <th width="80">新闻标题</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
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
            var url = "${path}/news/selectNews";
            /*列对应表*/
            var columns = [
                {data: null},
                {data: "title"},
                {data: null},
                {data: null}
            ];
            /*特殊列渲染*/
            var columnDefs = [
                {name: "id", targets: 0},
                {name: "title", targets: 1},
                {name: "createTime", targets: 2},
                {name: "updateTime", targets: 3},
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
                },
                {
                    //表示第2列
                    targets: 2,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data.createTime);
                    }
                },
                {
                    //表示第3列
                    targets: 3,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data.updateTime);
                    }
                }
            ];
            /*初始化dataTables*/
            dataTables(url, columns, columnDefs);
        </script>
        <script type="application/javascript">
            /*时间戳转时间*/
            function getMyDate(str) {
                var oDate = new Date(str),
                        oYear = oDate.getFullYear(),
                        oMonth = oDate.getMonth() + 1,
                        oDay = oDate.getDate(),
                        oHour = oDate.getHours(),
                        oMin = oDate.getMinutes(),
                        oSen = oDate.getSeconds(),
                        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
                return oTime;
            }

            function getzf(num) {
                if (parseInt(num) < 10) {
                    num = '0' + num;
                }
                return num;
            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>