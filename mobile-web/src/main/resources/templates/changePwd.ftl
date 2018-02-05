<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间_修改用户密码</title>
    <script src="/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
	<link rel="stylesheet" type="text/css" href="css/changePwd.css"/>
</head>
<body>
	<div class="header-footer">
		<div class="header">
			<div class="left">
				<div class="return">
                    <a href="/centerPage">
						<img src="img/back-white.png" />
					</a>
				</div>
			</div>
			<div class=" middle">
				<p>筛选</p>
			</div>
			<div class=" right">
				<div class="go">
					<!--<a href="">
						<p>完成</p>
					</a>-->
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="intext">
			<span>原始密码</span>
			<input type="password" placeholder="请输入旧密码" id="oldPwd"/>
		</div>
		<div class="intext">
			<span>新密码</span>
			<input type="password" placeholder="请输入6-12位密码" id="pwd"/>
		</div>
		<div class="intext">
			<span>确认密码</span>
			<input type="password" placeholder="再次确认新密码" id="rpwd"/>
		</div>
		
		<div class="btn" id="updatePwd">确定</div>
	</div>
</body>
<script>
	$("#updatePwd").click(function () {
        var oldPwd=$("#oldPwd").val();
        var pwd=$("#pwd").val();
        var rpwd=$("#rpwd").val();
        var pwdTest=/^(\w){6,12}$/;
        if(oldPwd == null || $.trim(oldPwd).length==0){
            alert("旧密码不能为空");
            return;
		}
		if(!pwdTest.test(pwd)){
            alert("请设置6-12位密码");
			return;
		}
        if(!pwdTest.test(rpwd)){
            alert("请设置6-12位密码");
            return;
        }
        if(!(pwd==rpwd)){
            alert("两次输入的新密码不一致");
            return;
		}
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"text",
            //请求URL
            url:"/user/changeUserPwd",
            //传入服务器端的参数值
            data:{oldPassword:oldPwd,password:pwd},
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
				if(data=="1"){
				    alert("修改成功");
				    window.location.href="/centerPage";
				}else if(data==2){
				    alert("修改失败");
				}else if(data=="3"){
				    alert("连接超时，请重新登录");
				    window.location.href="/login";
				}else if(data=="4"){
				    alert("旧密码输入错误");
				}
            },
            error:function(data){
				alert("请求失败");
            }
        });
    });

</script>
<script>
    function huiTui() {
        if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else {
            window.history.go(-1);
        }
    }
</script>
</html>