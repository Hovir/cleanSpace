<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
        <script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<style type="text/css">
			.content{
				margin-top: 45px;
				overflow: hidden;
			}
		</style>
	</head>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
                        <a href="javascript:void(0)"  onclick="huiTui()">
							<img src="/img/back-black.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p id="title"><#--环境保护（简称环保）--></p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="">
							<!--<img src="img/setting.png" />-->
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content" id="detail">
			<#--后台加载的新闻-->
		</div>
		<input type="hidden" value="${newId!'0'}" id="theNewId">
	</body>
<script>
	$(function () {
		var newId=$("#theNewId").val();
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"json",
            //请求URL
            url:"/news/selectNewsById",
            //传入服务器端的参数值
            data:{newId:newId},
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
                $("#title").html(data.title);
				$("#detail").html(data.details);
            },
            error:function(data){
				/*alert("请求失败");*/
            }
        });
    });
</script>
<script>
	function huiTui () {
		if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
			window.location.href = window.document.referrer;
		} else { window.history.go(-1); };
	}
</script>
</html>
