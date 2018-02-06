<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/register.css"/>
	</head>
	<script type="text/javascript">
		   $(function(){
        $(".user-name").click(function(){
            $(".user-name").css("border-color","#4DB748");
            $(".user-pwd").css("border-color","#dddddd");
            $(".user-yanzheng").css("border-color","#dddddd");
       });
        $(".user-pwd").click(function(){
            $(".user-pwd").css("border-color","#4DB748");
            $(".user-name").css("border-color","#dddddd");
            $(".user-yanzheng").css("border-color","#dddddd");
        });
         $(".user-yanzheng").click(function(){
            $(".user-yanzheng").css("border-color","#4DB748");
            $(".user-name").css("border-color","#dddddd");
            $(".user-pwd").css("border-color","#dddddd");
        });

    });
	</script>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
                        <a href="javascript:void(0)"  onclick="huiTui()">
							<img src="/img/back.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>注册</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="/login">
							<span>登录</span>
							<!--<img src= "img/setting.png" />-->
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
		    <div class="top-logo">
		    	<img src="img/logo.png" alt="" />
		    </div>
		    <div class="sign in">
		    	<form action="" method="">
		    		<input type="text" class="user-name" placeholder="输入手机号" onfocus="clearInfo()" id="phone" />
		    		<div>
		    			<input type="text" class="user-yanzheng" id="verfiy" onfocus="clearInfo()"/>
		    			<span class="yanzheng" id="quick1">验证码</span>
                        <div type="button" id="quick2" class="yanzheng" style="display: none;">
                            <span id="quick3" style="color: rgba(255,251,251,0.77);">60</span>秒
                        </div>
		    		</div>
		    				    		
		    		<input type="password" class="user-pwd" id="pwd" placeholder="用户密码" onfocus="clearInfo()" />
		    		
		    		<div class="agreement">
		    			<input type="checkbox" name="agreement" class="inp-agree" onfocus="clearInfo()"/><span>我已阅读并同意<em>环保空间用户协议</em></span>
		    		</div>
					<div id="info"></div>
		    		<div class="btn-login" id="registBut">立即注册</div>
                    <div class="forgetpwd"><a href="/goToForgetPwd">忘记密码？</a></div>
		    	</form>
		    </div>
		</div>
	</body>
<script>
    $("#quick1").click(function () {
        var phone = $("#phone").val(); //手机号
        if ($.trim(phone) == "") {
            $("#info").html("请输入手机号!").css("color", "red");
            return;
        }
        if (!checkPhone(phone)) {
            return;
        }
        //验证手机号是否存在
        $.ajax({
            url: "/user/isExist",
            type: "POST",
            dataType: "text",
            data: {"phone": phone},
            success: function (data) {
                if (data == "1") {
                    $("#info").html("手机号码已注册!").css("color", "red");
                    return;
                } else {
                    $("#info").html("");
                    getResetMessage(phone);
                }
            },
            error: function (data) {
                //alert('请求失败!');
            }
        });

        //发送验证码
        function getResetMessage(phone) {
            $("#quick2").show();
            $("#quick1").hide();
            $.ajax({
                url: "/user/getRegisterMessage",
                type: "POST",
                dataType: "json",
                data: {"phone": phone},
                success: function (data) {
                    return;
                },
                error: function (data) {
                    //alert('获取验证码请求失败!');
                }
            });
            var obj = this;
            var timer = setInterval(function () {
                var tir = $("#quick3");
                var temp = tir.html();
                if (temp > 0) {
                    temp = temp - 1;
                    tir.html(temp);
                }
                else {
                    $("#quick2").hide();
                    $("#quick1").show();
                    clearInterval(timer);//关闭定时器
                    tir.html(60);
                }
            }, 1000);
        }
    })

    //点击注册按钮
    $("#registBut").click(function () {
        if ( !$("[name='agreement']").prop("checked") ){
            alert("请同意环保空间用户协议");
            return;
        }
        var phone = $("#phone").val(); //手机号
        var verfiy = $("#verfiy").val(); //验证码
        var pwd = $("#pwd").val(); //密码第一次
        if ($.trim(phone) == "") {
            $("#info").html("请输入手机号!").css("color", "red");
            return;
        }
        if ($.trim(verfiy) == "") {
            $("#info").html("请输入验证码!").css("color", "red");
            return;
        }
        if (!checkPwd(pwd)) {
            return;
        }
        if (!checkPhone(phone)) {
            return;
        }
        $.ajax({
            url:"/user/userRegist",
            dataType:"text",
            data:{phone:phone,verfiy:verfiy,pwd:pwd},
            type:"POST",
            success:function (data) {
                if(data == "1"){
                    $("#info").html("手机号已注册!").css("color", "red");
                }else if(data == "2"){
                    $("#info").html("验证码失效或手机号不正确!").css("color", "red");
                }else if(data == "3"){
                    alert("注册成功!");
                    window.location.href = "/login";
                }else if(data == "4"){
                    $("#info").html("注册失败!").css("color", "red");
                }
            },
            error:function (data) {
                //alert("请求失败!!");
            }
        });
    });

    //验证手机号
    function checkPhone(phone) {
        var phoneTest = /^(((13[0-9]{1})|(17[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (!(phoneTest.test(phone))) {
            $("#info").html("手机号输入有误!!").css("color", "red");
            return false;
        } else {
            $("#info").html("");
            return true;
        }
    }

    //验证密码
    function checkPwd(password) {
        var pwdTest = /^(\w){5,20}$/;
        if (!(pwdTest.test(password))) {
            $("#info").html("密码只能是5-20个字符").css("color", "red");
            return false;
        } else {
            $("#info").html("");
            return true;
        }
    }

    //清除提示信息
    function clearInfo() {
        $("#info").html("");
    }
</script>
<script>
    function huiTui () {
        if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else { window.history.go(-1); };
    }
</script>
</html>
