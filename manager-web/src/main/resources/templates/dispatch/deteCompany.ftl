<#--
  Created by IntelliJ IDEA.
  User: 刘洪宇
  Date: 2018/1/19
  Time: 12:08
  To change this template use File | Settings | File Templates.
-->
<#assign path=springMacroRequestContext.getContextPath() />
<link rel="stylesheet" href="${path}/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<html>
<head>
    <title>demo</title>
    <#include "${path}/manager/_meta.ftl"/>
</head>
<body>
<!--/代码写在这里-->
<table class="table" style="margin: auto">
    <tr>
        <td width="200" class="va-t">
            <ul id="ztree" class="ztree"></ul>
            <div id="error"></div>
            <div class="pd-20">
                <div class="row cl">
                    <div class="col-9 col-offset-2">
                        <input class="btn btn-primary radius" type="button" onclick="sub()"
                               value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                        <button onClick="layer_close();" class="btn btn-default radius" type="button">
                            &nbsp;&nbsp;返回&nbsp;&nbsp;
                        </button>
                        <div id="create"></div>
                    </div>
                </div>
            </div>
        </td>
    </tr>
</table>
<!--/代码写在这里-->

<!--_footer 作为公共模版分离出去-->
<#include "${path}/manager/_footer.ftl"/>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${path}/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script>
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
        var id = ${id};
        $("#error").empty();
        if (zTreeId === "" || zTreeId === undefined) {
            $("#error").html("<span style=\"color: #FF5722;\">请先选择公司!</span>");
            return null;
        }
        $("#error").html("<span style=\"color: #FF5722;\">选择成功</span>");
        $.get("${path}/saveDeteCompany/"+zTreeId+"/"+id ,function () {
            alert("指派成功，请点击屏幕刷新按钮刷新页面");
            layer_close();
        })
    }
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>