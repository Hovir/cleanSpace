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
    <span class="c-999 en">&gt;</span><span class="c-666">空白页</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;" onclick="pageTurns('withdraw/withdrawList')" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">

        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr class="text-c">
                    <th width="50"></th>
                    <th width="100">公司名称</th>
                    <th width="100">提现金额</th>
                    <th width="150">申请时间</th>
                    <th width="150">操作时间</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody class="text-c">
                </tbody>
            </table>
        </div>
    <#--请在下方写此页面业务相关的脚本-->
        <script type="application/javascript" src="${path}/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
        <script type="application/javascript" src="${path}/lib/js/dataTables.js"></script>
        <script type="application/javascript">
            var url = "${path}/withdraw/withdrawList";
            var i = 1;
            /*列对应表*/
            var columns = [
                {data: "id"},
                {data: null},
                {data: "money"},
                {data: "createTime"},
                {data: "updateTime"},
                {data: null}
            ];
            /*特殊列渲染*/
            var columnDefs = [
                {name: "id", targets: 0},
                {name: "money", targets: 2},
                {name: "createTime", targets: 3},
                {name: "updateTime", targets: 4},
                {
                    //表示第1列
                    targets: 0,
                    //渲染函数
                    render: function (data) {
                        return i++;
                    }
                },
                {
                    //表示第2列
                    targets: 1,
                    //渲染函数
                    render: function (data) {
                        return data.company.name;
                    }
                },{
                    //表示第3列
                    targets: 2,
                    //渲染函数
                    render: function (data) {
                        return data/100;
                    }
                },
                {
                    //表示第4列
                    targets: 3,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data);
                    }
                },
                {
                    //表示第5列
                    targets: 4,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data);
                    }
                },
                {
                    //表示第6列
                    targets: 5,
                    //渲染函数
                    render: function (data) {
                        var pay = data.isPay;
                        var uid = data.id;
                        if(pay == 0){
                            return "<button class='btn-primary' onclick='updatePay("+uid+")'>同意提现</button>";
                        }else {
                            return "<button class='btn-default' style='cursor: not-allowed;' disabled >已提现</button>";
                        }
                    }
                },
                {
                    //禁用排序
                    orderable: false,
                    //指定的列
                    targets: [0, 4],
                }
            ];
            /*初始化dataTables*/
            dataTablesSearch(url, columns, columnDefs);

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

            function updatePay(id) {
                $.get("${path}/withdraw/updatePay/"+id ,function () {
                    alert("成功");
                    pageTurns("withdraw/withdrawList");
                })
            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>