<#--
  Created by IntelliJ IDEA.
  com.godfkc.pojo.User: 刘洪宇
  Date: 2018/1/3
  Time: 17:46
  To change this templates use File | Settings | File Templates.
-->
<!DOCTYPE HTML>
<#assign path=springMacroRequestContext.getContextPath() />
<html>
<head>
    <title>DCCSHOP-后台登录</title>

    <link type="text/css" href="${path}/assets/css/H-ui.min.css" rel="stylesheet" />
    <link type="text/css" href="${path}/assets/css/H-ui.login.css" rel="stylesheet"/>
    <link type="text/css" href="${path}/assets/css/style.css" rel="stylesheet"/>

    <link type="text/css" href="${path}/assets/css/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet"/>
    <link type="text/css" href="${path}/assets/adminLogin/adminLogin.css" rel="stylesheet"/>

    <script type="text/javascript" src="${path}/assets/adminLogin/js/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/assets/adminLogin/js/H-ui.min.js"></script>
    <script type="text/javascript" src="${path}/assets/adminLogin/adminLogin.js"></script>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value=""/>
<div class="header"><div class="header-title">后台管理系统</div></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <span class="col-xs-offset-3-title" style="color: #d2fbff;">欢迎登陆后台管理系统</span>
            </div>
        </div>
        <div class="form form-horizontal-div">
            <form id="form-login" class="form form-horizontal">
                <div class="row cl">
                    <div class="formControls col-xs-8">
                        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                        <input id="adminInputName" name="name" type="text" onblur="myInputName()" onfocus="myEmpty()" placeholder="账户" class="input-text size-L" value="" />
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8">
                        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                        <input id="adminInputPwd" name="password" type="password" onblur="myInputPwd()" onfocus="myEmpty()" placeholder="密码" class="input-text size-L" value="" />
                    </div>
                </div>
                <div class="row cl" id="row-cl-fail-message">
                    <#--<div id="fail_message">${login_fail_by_some}</div>-->
                </div>

                <div class="row cl"  id="row-cl-login">
                    <div class="formControls col-xs-8 col-xs-offset-3" id="row-cl-login-in">
                        <a class="btn btn-success radius size-L" id="submit-login">&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="footer">Copyright &copy; 2016-2018 DCCSHOP  沈阳东方快车网络科技版权所有</div>
</body>
</html>