<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/bespeak-login.css"/>
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
							<img src="img/back-black.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>预约检测</p>
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
		    		<input type="text" class="user-name" id="" placeholder="水晶环保检测卡号" />
		    		<input type="password" class="user-pwd" id="" placeholder="水晶环保检测密码" /> 
		    		<div class="activation"><span>激活水晶环保检测卡</span></div>
		    	</form>
		    </div>
		</div>
	</body>
</html>
