<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间</title>
    <script src="js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
	<link rel="stylesheet" type="text/css" href="css/personCenter.css"/>
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
				<p>个人中心</p>
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
		<div class="person-cont">
			<div class="person-img">
				<span class="font">头像</span>
				<a href="/cutHeadImg">
					<span class="img">
						<img src="${Session.userHeadImg!'img/center.png'}"/>
					</span>
                </a>
			</div>
			<div class="person-name">
				<span class="font">昵称</span>
				<input type="text" id="nickName" value="${Session.userName!''}" onclick="clearInfo()" onblur="updateNick()"/>
			</div>
			<div id="info"></div>
		</div>
		<#--<div class="btn">确认</div>-->
	</div>
</body>
<script>
	function updateNick() {
		var nickName = $("#nickName").val();
		var checkName = /^[0-9a-zA-Z\u4e00-\u9fa5_]{3,16}$/;
		if (!checkName.test(nickName)){
		    $("#info").html("昵称长度：3-16位 可以为汉字、数字、字母").css("color","red");
            $("#nickName").select();
			return ;
		}
		$.ajax({
			url:"/user/updateNameById",
			dateType:"text",
			type:"post",
			data:{name:nickName},
			success:function (data) {
				if (data == "1"){
				    alert("修改昵称失败");
				}
                if (data == "2"){
                    alert("连接超时,请重新登录");
                    window.location.href = "/login";
                }
                if (data == "3"){
                   // alert("修改昵称成功");
                }
            },
			error:function () {
				//alert("请求失败");
            }
		})
    }
    //清除提示信息
    function clearInfo() {
        $("#info").html("");
    }
</script>
</html>