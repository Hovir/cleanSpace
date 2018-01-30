<#assign path=springMacroRequestContext.getContextPath() />
<#--
  Created by IntelliJ IDEA.
  com.godfkc.pojo.User: 刘洪宇
  Date: 2018/1/3
  Time: 17:46
  To change this templates use File | Settings | File Templates.
-->
<html>
<head>
    <title>东方快车</title>
    <#include "${path}/manager/_meta.ftl"/>
</head>
<body>
<#--_header 作为公共模版分离出去-->
<#include "${path}/manager/_header.ftl"/>
<#--/_header 作为公共模版分离出去-->

<#--_menu 作为公共模版分离出去-->
<#include "${path}/manager/_menu.ftl"/>
<#--/_menu 作为公共模版分离出去-->

<#--/代码写在这里-->
<section class="Hui-article-box" id="addHtml">

</section>
<#--/代码写在这里-->

<#--_footer 作为公共模版分离出去-->
<#include "${path}/manager/_footer.ftl"/>
<#--/_footer /作为公共模版分离出去-->

<script>
    /*pageTurns("/userPage/starter");*/

    function pageTurns(url) {
        var data = {
            url: "/page/" + url
        };
        $.ajax({
            url: url,    //请求的url地址
            dataType: "html",   //返回格式为html
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",
            async: true,    //请求是否异步，默认为异步，这也是ajax重要特性
            data: data,    //参数值
            type: "POST",   //请求方式
            success: function (req) {
                $("#addHtml").empty();
                $("#addHtml").html(req);
            }
        });
    }
</script>
</body>
</html>