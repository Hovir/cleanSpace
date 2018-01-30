<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" >
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>乐美优享</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/forgetpwd.css"/>
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
						<a href="">
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
		    		<input type="text" class="user-name" placeholder="输入手机号" id="" />
		    		<div>
		    			<input type="text" class="user-yanzheng"/>
		    			<span class="yanzheng">验证码</span>
		    		</div>
		    				    		
		    		<input type="password" class="user-pwd" id="" placeholder="设置登录密码" />
		    		<input type="password" class="user-pwd" id="" placeholder="再次输入密码" />		    		
		    		<div class="btn-login">确认登陆</div>          
		    	</form>
		    </div>
		</div>
	</body>
</html>
