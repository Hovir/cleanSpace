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
    <span class="c-999 en">&gt;</span><span class="c-666">卡片管理</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;" onclick="pageTurns('card/card')" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">

        <#--<div class="cl pd-5 bg-1 bk-gray mt-20">-->
				<#--<span class="l">-->
				<#--<a href="javascript:;" onclick="selectButton(1)" class="btn btn-danger radius"> 已激活</a>-->
				<#--<a href="javascript:;" onclick="selectButton(2)" class="btn btn-primary radius"> 未激活</a>-->
				<#--</span>-->
        <#--</div>-->
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr>
                    <th width="100">卡号</th>
                    <th width="100">创建时间</th>
                    <th width="75">状态</th>
                    <th width="100">公司</th>
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
            var url = "${path}/card/cardList?status=1";
            var i = 1;
            /*列对应表*/
            var columns = [
                {data: "num"},
                {data: null},
                {data: null},
                {data: "company.name"}
            ];
            /*特殊列渲染*/
            var columnDefs = [
                {name: "num", targets: 0},
                {name: "createTime", targets: 1},
                {name: "status", targets: 2},
                {name: "company.name", targets: 3},
                {
                    //禁用排序
                    orderable: false,
                    //指定的列
                    targets: [0, 3]
                },
                {
                    //表示第2列
                    targets: 1,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data.createTime);
                    }
                },
                {
                    //表示第3列
                    targets: 2,
                    //渲染函数
                    render: function (data) {
                        return typeDate(data.status);
                    }
                }
            ];
            /*初始化dataTables*/
            dataTables(url, columns, columnDefs);
        </script>
        <script type="application/javascript">
            function selectButton(status) {
                $('.table').dataTable();
                $('.table').dataTable().fnClearTable();
                url = "${path}/card/cardList?status="+status;
               i = 1;
                /*列对应表*/
                 columns = [
                    {data: null},
                    {data: "num"},
                    {data: null},
                    {data: null},
                    {data: "company.name"}
                ];
                /*特殊列渲染*/
                 columnDefs = [
                    {name: "id", targets: 0},
                    {name: "num", targets: 1},
                    {name: "createTime", targets: 2},
                    {name: "status", targets: 3},
                    {name: "company.name", targets: 4},
                    {
                        //表示第0列
                        targets: 0,
                        //渲染函数
                        render: function (data) {
                            return i++;
                        }
                    },
                    {
                        //禁用排序
                        orderable: false,
                        //指定的列
                        targets: [0, 4]
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
                            return typeDate(data.status);
                        }
                    }
                ];
                /*初始化dataTables*/
                dataTables(url, columns, columnDefs);
            }

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

            function typeDate(num) {
                if(num == 0){
                    return "停用";
                }else if(num == 1){
                    return "未激活";
                }else {
                    return "已激活";
                }
            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>