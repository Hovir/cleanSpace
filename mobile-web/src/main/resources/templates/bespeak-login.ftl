<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="/js/jquery.min.js"></script>
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
                        <a href="javascript:void(0)"  onclick="huiTui()">
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
		    		<input type="text" class="user-name" id="num" placeholder="水晶环保检测卡号" />
		    		<input type="password" class="user-pwd" id="password" placeholder="水晶环保检测密码" />
		    		<div class="activation" onclick="activate();"><span>激活水晶环保检测卡</span></div>
		    	</form>
		    </div>
		</div>
	</body>
<script>
 function  activate() {
	 var num=$("#num").val();
	 var password=$("#password").val();
	 var numTest=/^(\d){12}$/;
	 var passwordTest=/^(\d){6}$/;
	 if(!numTest.test(num)){
	     alert("请输入12位数字卡号");
	     return;
	 }
     if(!passwordTest.test(password)){
         alert("请输入6位数字密码");
         return;
     }
     $.ajax({
         //请求类型
         type:"POST",
         //预期服务器返回的数据类型
         dataType:"json",
         //请求URL
         url:"/card/checkCard",
         //传入服务器端的参数值
         data:{num:num,password:password},
         //从ajax异步对象中获取服务器响应的html数据
         success:function(data){
			if(data.flag=="1"){
			    var card=$.parseJSON(data.card);
			    window.location.href="/pay?cardId="+card.id;
			}else if(data.flag=="2"){
			    alert("卡号密码不匹配");
			}
         },
         error:function(data){

         }
     });
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
