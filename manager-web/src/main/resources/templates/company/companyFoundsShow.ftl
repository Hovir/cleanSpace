<#--
  Created by IntelliJ IDEA.
  User: 刘洪宇
  Date: 2018/1/19
  Time: 12:08
  To change this template use File | Settings | File Templates.
-->
<!DOCTYPE HTML>
<#assign path=springMacroRequestContext.getContextPath() />
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <#--[if lt IE 9]-->
    <script type="text/javascript" src="${path}/lib/html5.js"></script>
    <script type="text/javascript" src="${path}/lib/respond.min.js"></script>
    <#--[endif]-->
    <link href="${path}/lib/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="${path}/lib/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="${path}/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <#--[if IE 6]-->
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script><![endif]-->
    <title>查看-企业管理</title>
</head>
<body>
<article class="cl pd-20">
    <div>
        <div class="cl pd-20" style=" background-color:#5bacb6">
            <img class="avatar size-XL l" src="${path}/lib/static/h-ui/images/user.png">
            <dl style="margin-left:80px; color:#fff">
                <dt><span class="f-18">张三</span> <span class="pl-10 f-12">余额：40</span></dt>
                <dd class="pt-10 f-12" style="margin-left:0">这家伙很懒，什么也没有留下</dd>
            </dl>
        </div>
        <div class="pd-20">
            <table class="table">
                <tbody>
                <tr>
                    <th class="text-r" width="80">性别：</th>
                    <td>男</td>
                </tr>
                <tr>
                    <th class="text-r">手机：</th>
                    <td>13000000000</td>
                </tr>
                <tr>
                    <th class="text-r">邮箱：</th>
                    <td>admin@mail.com</td>
                </tr>
                <tr>
                    <th class="text-r">地址：</th>
                    <td>北京市 海淀区</td>
                </tr>
                <tr>
                    <th class="text-r">注册时间：</th>
                    <td>2014.12.20</td>
                </tr>
                <tr>
                    <th class="text-r">积分：</th>
                    <td>330</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</article>
<#--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${path}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${path}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${path}/lib/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${path}/lib/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<!--/_footer /作为公共模版分离出去-->
</body>
</html>