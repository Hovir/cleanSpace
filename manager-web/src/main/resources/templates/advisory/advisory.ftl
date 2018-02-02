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
    <span class="c-999 en">&gt;</span><span class="c-666">资讯管理</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;"
       onclick="pageTurns('advisory/advisory')" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a></nav>
<div class="Hui-article">
    <article class="cl pd-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<a href="javascript:;" onclick="deleteAll(this)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
				<a href="javascript:;" class="btn btn-primary radius" data-title="添加资讯"
                   onclick="layer_open('添加资讯','/news/openAddNews')"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a>
				</span>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr>
                    <th width="25"><input onclick="switchAll()" type="checkbox"></th>
                    <th width="200">新闻标题</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th width="50">功能</th>
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
                        return "<input type=\"checkbox\" name=\"formvalue\" value=\"" + data.id + "\"/>";
                    }
                },
                {
                    //表示第0列
                    targets: 4,
                    //渲染函数
                    render: function (data) {
                        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"layer_open('修改资讯','/news/updateNews/" + data.id + "')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>" +
                                "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"del(this," + data.id + ")\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
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
                        return getMyDate(data.updateTime);
                    }
                }
            ];
            /*初始化dataTables*/
            dataTables(url, columns, columnDefs);
        </script>
        <script type="application/javascript">
            //复选框选择转换
            function switchAll() {
                var roomids = document.getElementsByName("formvalue");
                for (var j = 0; j < roomids.length; j++) {
                    roomids.item(j).checked = !roomids.item(j).checked;
                }
            }

            //批量删除
            function deleteAll(obj) {
                layer.confirm('确认要删除吗？', function (index) {
                    var id = new Array();
                    var groupCheckbox = $("input[name='formvalue']");
                    for (var i = 0; i < groupCheckbox.length; i++) {
                        if (groupCheckbox[i].checked) {
                            id.push(groupCheckbox[i].value);
                        }
                    }
                    $.post("${path}/news/deleteNews", {id: id}, function (req) {
                        if (req) {
                            $(obj).parents("tr").remove();
                            layer.msg('已删除!', {icon: 1, time: 1000});
                            pageTurns("advisory/advisory");
                        } else {
                            $(obj).parents("tr").remove();
                            layer.msg('删除失败!', {icon: 5, time: 1000});
                            pageTurns("advisory/advisory");
                        }
                    })
                })
            }

            //删除
            function del(obj, i) {
                layer.confirm('确认要删除吗？', function (index) {
                    var id = new Array();
                    id.push(i);
                    $.post("${path}/news/deleteNews", {id: id}, function (req) {
                        if (req) {
                            $(obj).parents("tr").remove();
                            layer.msg('已删除!', {icon: 1, time: 1000});
                            pageTurns("advisory/advisory");
                        } else {
                            $(obj).parents("tr").remove();
                            layer.msg('删除失败!', {icon: 5, time: 1000});
                            pageTurns("advisory/advisory");
                        }
                    })
                })
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
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>