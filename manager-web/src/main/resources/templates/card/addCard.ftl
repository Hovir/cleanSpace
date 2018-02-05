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
                    <div id="error"></div>
                </td>
                <td class="va-t">
                    <div class="pd-20">
                        <div class="row cl">
                            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>数量：</label>
                            <div class="formControls col-5">
                                <select class="select" id="num">
                                    <option value="50" selected="selected">50</option>
                                    <option value="100">100</option>
                                    <option value="150">150</option>
                                    <option value="200">200</option>
                                </select>
                            </div>
                            <div class="col-5"></div>
                        </div>
                        <br/>
                        <div class="row cl">
                            <div class="col-9 col-offset-2">
                                <input class="btn btn-primary radius" type="button" onclick="sub()"
                                       value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                                <div id="create"></div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>

    <#--请在下方写此页面业务相关的脚本-->
        <script type="text/javascript" src="${path}/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>

        <script type="text/javascript">
            //公司id
            var zTreeId = "";
            //zTree配置文件
            var setting = {
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: zTreeonCheck
                }
            };

            function zTreeonCheck(event, treeId, treeNode) {
                zTreeId = "";
                var treeObj = $.fn.zTree.getZTreeObj("ztree"),
                        nodes = treeObj.getCheckedNodes(true);
                for (var i = 0; i < nodes.length; i++) {
                    zTreeId = nodes[i].id;
                }
            }

            //json数据
            $.post("${path}/card/allCompany", function (obj) {
                $.fn.zTree.init($("#ztree"), setting, obj);
            }, "json");
        </script>
        <script>
            function sub() {
                var data;
                $("#error").empty();
                if (zTreeId === "" || zTreeId === undefined) {
                    $("#error").html("<span style=\"color: #FF5722;\">请先选择公司!</span>");
                    return null;
                }
                $("#create").html("<span style=\"color: #FF5722;\">请稍等,卡号生成中...!</span>");
                window.open("${path}/card/insertCard/" + zTreeId + "/" + $("#num").val());
            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>