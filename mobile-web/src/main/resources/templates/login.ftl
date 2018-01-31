<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>乐美优享</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
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
                    <div class="btn-login" id="login-btn">立即登录</div>
                    <div class="forgetpwd"><a href="">忘记密码？</a></div>
		    	</form>
		    </div>
		</div>

	<script type="text/javascript">
		$("#login-btn").click(function(){
			var phone = $("#phone").val();
			var password = $("#password").val();

			$.ajax({
				url:"/user/login",
				data:{phone:phone,password:password},
				type:"post",
				dataType:"json",
				success:function (data) {
					if(data.flag==0){
					    alert(data.info);
					}

					if(data.flag==1){
					    alert(data.user);
					}
                }
			});
        });
	</script>
	</body>
</html>
