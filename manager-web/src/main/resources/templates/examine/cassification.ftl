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
    <span class="c-999 en">&gt;</span><span class="c-666">修改佣金</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;"
       onclick="pageTurns('examine/cassification')" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">
        <div class="mt-20">
            <table align="center" class="table table-border table-bordered table-bg table-hover table-sort" id="u1">
                <thead>
                <tr class="text-c">
                    <th>店铺类型</th>
                    <th>id</th>
                    <th>佣金</th>
                    <th>操作</th>
                </tr>
                </thead>

            </table>
        </div>



    <#--请在下方写此页面业务相关的脚本-->
        <script type="text/javascript" src="${path}/lib/jquery/jquery-3.2.1.js"></script>
        <script type="text/javascript">
            $(function () {
                $.ajax({
                    url: "/selectGrade",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        $.each(data, function (index, value) {
                            var $trNew = $("<tr class=\"text-c\">" +
                                    "<td>" + value.name + "</td>" +
                                    "<td>" + value.id + "</td>" +
                                    "<td>" + value.commision + "</td>" +
                                    "<td><button class='button' id='but' onclick='turn(" + value.id + ")'>修改</button></td>" +
                                    "</tr>");

                            $("#u1").append($trNew);

                        })
                    }
                });
            });

            function turn(id) {
                pageTurns("selectById/" + id);

            }

        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
<#include "${path}/manager/_copyright.ftl"/>
</div>