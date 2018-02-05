<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间</title>
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
		<div class="person-cont">
			<div class="person-img">
				<span class="font">头像上传</span>
				<span class="img">
					<img src="${Session.userHeadImg!'img/center.png'}"/>
				</span>
			</div>
			<div class="person-name">
				<span class="font">昵称</span>
				<input type="text" value="${Session.userName!''}"/>
			</div>
		</div>
		<div class="btn">确认</div>
	</div>
</body>
</html>