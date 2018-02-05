<#--
  Created by IntelliJ IDEA.
  User: 刘洪宇
  Date: 2018/1/19
  Time: 12:08
  To change this template use File | Settings | File Templates.
-->
<#assign path=springMacroRequestContext.getContextPath() />
<link rel="stylesheet" href="${path}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

<nav class="breadcrumb"><i class="Hui-iconfont"></i>
    <a href="javascript:;" onclick="pageTurns('manager/_home')" class="maincolor">首页</a>
    <span class="c-999 en">&gt;</span><span class="c-666">制卡</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;"
       onclick="pageTurns('card/addCard')" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">

        <table class="table">
            <tr>
                <td width="200" class="va-t">
                    <ul id="ztree" class="ztree"></ul>
                </td>
                <td>
                    <div class="pd-20">
                        <form action="" method="post" class="form form-horizontal" id="form-user-add">
                            <div class="row cl">

                            </div>
                            <div class="row cl">

                            </div>
                        </form>
                    </div>
                </td>
            </tr>
        </table>

    <#--请在下方写此页面业务相关的脚本-->
        <script type="text/javascript" src="${path}/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>

        <script type="text/javascript">
            var setting = {
                view: {
                    dblClickExpand: false,
                    showLine: false,
                    selectedMulti: false
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPId: ""
                    }
                },
                callback: {
                    beforeClick: function (treeId, treeNode) {

                    }
                }
            };

            var zNodes = [
                {id: 1, pId: 0, name: "一级分类", open: true},
                {id: 11, pId: 1, name: "二级分类"},
                {id: 111, pId: 11, name: "三级分类"},
                {id: 112, pId: 11, name: "三级分类"},
                {id: 113, pId: 11, name: "三级分类"},
                {id: 114, pId: 11, name: "三级分类"},
                {id: 115, pId: 11, name: "三级分类"},
                {id: 12, pId: 1, name: "二级分类 1-2"},
                {id: 121, pId: 12, name: "三级分类 1-2-1"},
                {id: 122, pId: 12, name: "三级分类 1-2-2"},
            ];

            var code;

            function showCode(str) {
                if (!code) code = $("#code");
                code.empty();
                code.append("<li>" + str + "</li>");
            }

            $(document).ready(function () {
                var t = $("#treeDemo");
                t = $.fn.zTree.init(t, setting, zNodes);
                demoIframe = $("#testIframe");
                demoIframe.bind("load", loadReady);
                var zTree = $.fn.zTree.getZTreeObj("tree");
                zTree.selectNode(zTree.getNodeByParam("id", '11'));
            });
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>