<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>乐美优享</title>
		<script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/login.css"/>
	</head>
	<script type="text/javascript">
		   $(function(){
        $(".user-name").click(function(){
            $(".user-name").css("border-color","#4DB748");
            $(".user-pwd").css("border-color","#dddddd");
       });
        $(".user-pwd").click(function(){
            $(".user-pwd").css("border-color","#4DB748");
            $(".user-name").css("border-color","#dddddd");

        });

    });
	</script>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="">
							<!--<img src="img/return.png" />-->
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>登录</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="">
							<span>注册</span>
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
		    		<input type="text" class="user-name" id="phone" placeholder="输入手机号" maxlength="11"/>
		    		<input type="password" class="user-pwd" id="password" placeholder="用户密码" />
                    <div class="btn-login" id="doLogin">立即登录</div>
                    <div class="forgetpwd"><a href="/goToForgetPwd">忘记密码？</a></div>
		    	</form>
		    </div>
		</div>
	</body>
<script>
	$("#doLogin").click(function () {
        var phone=$("#phone").val();
        var password=$("#password").val();
        if(testLogin()){
            $.ajax({
                //请求类型
                type:"POST",
                //预期服务器返回的数据类型
                dataType:"json",
                //请求URL
                url:"/user/doLogin",
                //传入服务器端的参数值
                data:{phone:phone,password:password},
                //从ajax异步对象中获取服务器响应的html数据
                success:function(data){
					if(data){
					    window.location.href="/index";
					}else {
					    alert("手机号或密码错误，请重新输入！");
					}
                },
                error:function(data){
					alert("请求失败");
                }
            });
        }
	});


	function testLogin() {
        var phone=$("#phone").val();
        var password=$("#password").val();
        var phoneTest = /^[a-zA-Z0-9_-]{4,50}$/;
        var  pwdTest=/^(\w){4,50}$/;
        if(!phoneTest.test(phone)){
            alert("电话格式不正确");
            return false;
        }else if(!pwdTest.test(password)){
            alert("密码格式不正确");
            return false;
        }else {
            return true;
        }
    }
</script>
</html>
