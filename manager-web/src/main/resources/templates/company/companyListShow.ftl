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
    <title>详情-企业管理</title>
</head>
<body>
<article class="cl pd-20">
    <div>
        <input type="hidden" id="" name="" value="${companyShow.id!''}"/>
        <div class="cl pd-20" style=" background-color:#5bacb6">
            <img class="avatar size-XL l" src="${companyShow.imgUrl!''}" height="50" width="50">
            <dl style="margin-left:80px; color:#fff">
                <dt><span class="f-18">${companyShow.name!'公司名'}</span> <span class="pl-10 f-12"></span></dt>
                <dd class="pt-10 f-12" style="margin-left:0"></dd>
            </dl>
        </div>
        <div class="pd-20">
            <table class="table">
                <tbody>
                <tr>
                    <th class="text-r">公司级别：</th>
                    <td>${companyShow.level.name!''}</td>
                </tr>
                <tr>
                    <th class="text-r">加入时间：</th>
                    <td id="companyCreateTime">${(companyShow.createTime)!''}</td>
                </tr>
                <tr>
                    <th class="text-r">公司简介：</th>
                    <td>${(companyShow.profile)!''}</td>
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
<script type="application/javascript">
    /*时间戳转时间*/
    var $companyCreateTime=$("#companyCreateTime").html();
    alert("$companyCreateTime="+$companyCreateTime);
    alert("length="+$companyCreateTime.length)
    if($companyCreateTime.length!=0){
        alert("1="+$companyCreateTime)
        var time=getMyDate(${companyShow.createTime});
        alert("time="+time);
        $("#companyCreateTime").html(time);
    }
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
</body>
</html>