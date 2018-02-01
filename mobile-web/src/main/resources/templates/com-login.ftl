<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>乐美优享</title>
		<script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/com-login.css"/>
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
			<!--<div class="header">
				<div class="left">
					<div class="return">
						<a href="">
							<!--<img src="img/return.png" />-->
						<!--</a>
					</div>
				</div>
				<div class=" middle">
					<p>登录</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="">
							<span>注册</span>-->
							<!--<img src= "img/setting.png" />-->
						<!--</a>
					</div>
				</div>-->
			<!--</div>-->
			<div class="footer">
				<div class="part">
					<div class="part-cont">
						<a class="" href="index.ftl">
							<span class="icon"><img src="img/homepage.png"/></span>
							<span class="font">首页</span>
						</a>
					</div>
				</div>
				<div class="part">
					<div class="part-cont">
						<a class="" href="case.ftl">
							<span class="icon"><img src="img/green-business.png"/></span>
							<span class="font active">企业</span>
						</a>
					</div>
				</div>
				<div class="part">
					<div class="part-cont">
						<a class="" href="">
							<span class="icon"><img src="img/center.png"/></span>
							<span class="font">个人</span>
						</a>
					</div>
				</div>
				
			</div>
		<div class="content">
		    <div class="top-logo">
		    	<img src="img/logo.png" alt="" />
		    </div>
		    <div class="sign in">
		    	<form action="" method="">
		    		<input type="text" class="user-name" id="comAccount" placeholder="企业账户" />
		    		<input type="password" class="user-pwd" id="comPwd" placeholder="企业密码" />
					<br/>
					<span style="color: red;text-align: center;margin-top: 5px;">#这里是错误提示信息</span>
		    	</form>
		    </div>
		</div>

	</body>
    <script type="text/javascript">
        $("#comPwd").blur(function(){
            var comAccount=$("#comAccount").val();
            var comPwd=$("#comPwd").val();
            $.post("/company/loginAjax",{"comAccount":comAccount,"comPwd":comPwd},function (result){
                if(result){
                    alert("success!!!!");
                }
            });

        })
    </script>
</html>
