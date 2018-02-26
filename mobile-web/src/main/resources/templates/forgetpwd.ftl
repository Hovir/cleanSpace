<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>乐美优享</title>
		<script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/forgetpwd.css"/>
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
							<img src="img/back.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>忘记密码</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="">
							<!--<span>注册</span>-->
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
		    		<input type="text" class="user-name" placeholder="输入手机号" id="phone" onfocus="clearInfo()" />
		    		<div>
		    			<input type="text" class="user-yanzheng" id="verfiy" onfocus="clearInfo()"/>
		    			<span class="yanzheng" id="quick1">验证码</span>
                        <div type="button" id="quick2" class="yanzheng" style="display: none;">
                            <span id="quick3" >60</span>秒
                        </div>
		    		</div>
		    				    		
		    		<input type="password" class="user-pwd" id="pwd" onfocus="clearInfo()" placeholder="设置登录密码" />
		    		<input type="password" class="user-pwd" id="pwdAgain" onfocus="clearInfo()" placeholder="再次输入密码" />
					<div id="info"></div>
		    		<div class="btn-login" id="confirmLogin">确认登陆</div>
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
                    if (data != "1") {
                        $("#info").html("手机号码未注册!").css("color", "red");
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

        //点击确认登录
        $("#confirmLogin").click(function () {
            var phone = $("#phone").val(); //手机号
            var verfiy = $("#verfiy").val(); //验证码
            var pwd = $("#pwd").val();
            var pwdAgain = $("#pwdAgain").val();
            if ($.trim(phone) == "") {
                $("#info").html("请输入手机号!").css("color", "red");
                return;
            }
            if ($.trim(verfiy) == "") {
                $("#info").html("请输入验证码!").css("color", "red");
                return;
            }
            if (!checkPhone(phone)) {
                return;
            }
            if (!checkPwd(pwd)) {
                return;
            }
            if (pwd != pwdAgain) {
                $("#info").html("两次密码不一致!").css("color", "red");
                return;
            }
            $.ajax({
                url:"/user/confirmLogin",
                dataType:"text",
                data:{phone:phone,verfiy:verfiy,password:pwd},
                type:"POST",
                success:function (data) {
                    if(data == "1"){
                        $("#info").html("手机号未注册!").css("color", "red");
                        return ;
                    }else if(data == "2"){
                        $("#info").html("验证码失效或手机号不正确!").css("color", "red");
                        return ;
                    }else if(data == "4"){
						alert("重置密码失败!");
						window.location.href = "/goToForgetPwd";
                    }else if(data == "3"){
						alert("重置密码成功!");
						window.location.href = "/";
					}
                },
                error:function (data) {
                    //alert("请求失败!!");
                }
            });
        })

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

        function huiTui () {
            if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
                window.location.href = window.document.referrer;
            } else { window.history.go(-1); };
        }

        //清除提示信息
        function clearInfo() {
            $("#info").html("");
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
	</script>
</html>
