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
    <span class="c-999 en">&gt;</span><span class="c-666">无治理报告</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;" onclick="pageTurns('dispatch/notGovernReport')" title="刷新" >
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr>
                <#-- <th width="25"><input type="checkbox"></th>-->
                <#--<th width="80">订单编号</th>-->
                    <th>ID</th>
                    <th>姓名</th>
                    <th>电话</th>
                <#--<th>省</th>
                <th>城市</th>
                <th>区县</th>-->
                    <th>详细地址</th>
                    <th>委托时间</th>
                    <th>备注</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>变更时间</th>
                    <th>服务公司</th>
                    <th>报告图片</th>
                    <th>添加治理报告</th>
                    <th>选择治理公司</th>
                </tr>
                </thead>
            </table>
        </div>

    <#--请在下方写此页面业务相关的脚本-->
        <script type="application/javascript" src="${path}/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
        <script type="application/javascript" src="${path}/lib/js/dataTables.js"></script>
        <script type="application/javascript">
            var url = "${path}/findOrderByTypeAndStatus?type=2&status=1";
            /*列对应表*/
            var columns = [
                {data: "id"},
                {data: "name"},
                {data: "phone"},
                {data: null/*+"city"+"district"+"address"*/},
                /*{data: "city"},
                {data: "district"},
                {data: "address"},*/
                {data: "appointmentTime"},
                {data: "remark"},
                {data: "status"},
                {data: "createTime"},
                {data: "updateTime"},
                {data: null},
                {data: "report"},
                {data: null},
                {data:null}
            ];
            /*特殊列渲染*/
            var columnDefs = [
                {name: "id", targets: 0},
                {name: "name", targets: 1},
                {name: "phone", targets: 2},
                {name: "state", targets: 3},
                /*  {name: "city", targets: 4},
                  {name: "district", targets: 5},
                  {name: "address", targets: 6},*/
                {name: "appointmentTime", targets: 4},
                {name: "remark", targets: 5},
                {name: "status", targets: 6},
                {name: "createTime", targets: 7},
                {name: "updateTime", targets: 8},
                {name: "id", targets: 9},
                /*{
                    //表示第0列
                    targets: 0,
                    //渲染函数
                    render: function (data) {
                        return "<td><input type=\"checkbox\" name=\"cb\" value=\"" + data.id + "\"/></td>";
                    }
                },*/ {
                    //禁用排序
                    orderable: false,
                    //指定的列
                    targets: [0]
                },
                {
                    //表示第6列
                    targets: 6,
                    //渲染函数
                    render: function (data) {
                        return typeData(data);
                    }
                },
                {
                    //表示第4列
                    targets: 4,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data);
                    }
                },
                {
                    //表示第7列
                    targets: 7,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data);
                    }
                },
                {
                    //表示第8列
                    targets: 8,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data);
                    }
                },
                {
                    //表示第3列
                    targets: 3,
                    //渲染函数
                    render: function (data) {
                        return data.state+data.city +data.district + data.address;
                    }
                },
                {
                    //表示第10列
                    targets: 10,
                    //渲染函数
                    render: function (data) {
                        if(data != null){
                            return "<img src='" + data + "' class='layui-upload-img radius' height='50' width='50'>"
                        }else{
                            return "";
                        }

                    }
                },
                {
                    //表示第11列
                    targets: 11,
                    //渲染函数
                    render: function (data) {
                        var company1 = data.company;
                        if(company1 == null){
                            return "暂时无法添加";
                        }
                        return "<a  onClick=\"layer_open('添加治理报告','/toAddPicture/" + data.id + "')\")\" href=\"javascript:;\" >添加</a>";

                    }
                },
                {
                    //表示第12列
                    targets: 12,
                    //渲染函数
                    render: function (data) {
                        var company = data.company;
                        if(company == null) {
                            return "<button class='btn-primary'  onClick=\"layer_open('添加治理公司','/addCompany/" + data.id + "')\")\" href=\"javascript:;\" >选择</button>";
                        }
                        return "<button class='btn-default' onclick=\"yyy()\">选择</button>";
                    }
                },
                {
                    //表示第9列
                    targets: 9,
                    //渲染函数
                    render: function (data) {
                        var companyName = data.company;
                        if(companyName != null) {
                            return data.company.name;
                        }
                        return "";
                    }
                },
            ];
            /*初始化dataTables*/
            dataTables(url, columns, columnDefs);
        </script>
        <script type="application/javascript">
            /*时间戳转时间*/
            function getMyDate(str) {
                var oDate = new Date(str);
                oYear = oDate.getFullYear(),
                        oMonth = oDate.getMonth() + 1,
                        oDay = oDate.getDate(),
                        /*oHour = oDate.getHours(),
                        oMin = oDate.getMinutes(),
                        oSen = oDate.getSeconds(),*/
                        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) /*+ ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen)*/;//最后拼接时间
                return oTime;
            }

            function getzf(num) {
                if (parseInt(num) < 10) {
                    num = '0' + num;
                }
                return num;
            }
            function typeData(num) {
                if (num == 0){
                    return "取消";
                }else if( num == 1){
                    return "预约中";
                }else {
                    return "出报告";
                }
            }

            function toAddPicture(id) {
                window.location.href="${path}/toAddPicture?id="+id
            }

            function yyy() {
                alert("已有公司，无法重复添加");
            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
<#include "${path}/manager/_copyright.ftl"/>
</div>