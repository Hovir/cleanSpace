<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>乐美优享</title>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/bankCard.css"/>
		<script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="">
							<img src="img/back-white.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>银行卡</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="">
							<p>明细</p>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="settext bindcard">
				<span>卡号</span>
				<input type="text" name="" id="" value="6481****3210"/>
				<input type="text" name="" id="" value="中国工商银行 借记卡" class="cardtype"/>
			</div>
			<div class="settext">
				<span>手机号</span>
				<input type="text" name="" id="" value="" placeholder="请输入银行预留手机号"/>
			</div>
			<div class="settext">
				<span>验证码</span>
				<input type="text" name="" id="" value="" placeholder="请输入验证码" class="getnum"/>
				<span class="getnumber">
					<div id="quick1">获取验证码</div>
					<div type="button" id="quick2" style="display: none;">
					    <span id="quick3" >60</span>秒
					</div>
				</span>
			</div>
			<div class="card-btn">
				解除绑定
			</div>
		</div>
		<script type="text/javascript">
			$("#quick1").click(function(){
				$("#quick2").show(); 
				$("#quick1").hide(); 
				var obj = this; 
				var timer = setInterval(function () { 
					var tir = $("#quick3"); 
					var temp = tir.html(); 
					if (temp > 0) { 
						temp = temp - 1; tir.html(temp); 
					} else { 
						$("#quick2").hide(); 
						$("#quick1").show(); 
						clearInterval(timer);//关闭定时器 
						tir.html(60); 
					} 
				}, 1000);
			})
			 
		</script>
	</body>
</html>
