<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间_筛选</title>
	<#assign base=springMacroRequestContext.contextPath/>
    <link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
	<link rel="stylesheet" type="text/css" href="css/screen.css"/>
    <script src="js/jquery.min.js"></script>
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
		<div class="screen">
			<div class="sc-font">上传选择</div>
			<div class="sc-tab" id="select">
				<span class="active" onclick="selectThis(1,this)">未上传</span>
				<span onclick="selectThis(2,this)">已上传</span>
			</div>
			<div class="sc-font">时间选择</div>
			<div class="sc-tab">
				<input type="date" name="date1" id="date1" value="" />
				至
				<input type="date" name="date2" id="date2" value="" />
			</div>
			
		</div>
		<div class="sc-btn" onclick="javascript:window.location.reload();">重置</div>
		<div class="btn" onclick="doFilterWithData()">确定</div>
	</div>
</body>
<script>
	function selectThis(num,obj){
	    if(num===1){
		$(obj).addClass("active");
	    $(obj).next().removeClass("active");
		}
		if(num===2){
	        $(obj).addClass("active");
	        $(obj).prev().removeClass("active");
		}
	}
	function doFilterWithData() {
		var selected= $(".active").html();
		var status=1;
		if(selected=="已上传"){
            status=2;
		}else {
            status = 1;
        }
		var date1=$("#date1").val();
		var date2=$("#date2").val();
		if(null==date1||""==date1) date1= "2018-02-02";
		if(null==date2||""==date2) date2= "2028-02-02";
		if(date1>date2){
		    window.location.reload();
		}else{
		window.location.href="${base}/order/orderFilter/"+status+"/"+date1+"/"+date2;

		}
    }
</script>
</html>