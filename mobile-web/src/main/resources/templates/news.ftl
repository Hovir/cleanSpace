<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
        <script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/news.css"/>
	</head>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="/index">
							<img src="/img/back-black.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>环保资讯</p>
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
		<div class="content">
			<div class="product-type">
				<#--<div class="col-detail">
					<div class="detail">
						<img src="/img/case.jpg"/>
						<div class="introduce">
							<div class="introduce-detail">圣罗兰口红YL圣罗兰口红YL圣罗兰口红圣罗兰口红圣罗兰口红YL圣罗兰口红YL圣罗兰口红YL</div>
							<div class="introduce-time">
								<date>2017-12-12</date>
								<date>12:12</date>
							</div>
						</div>
					</div>
				</div>
				<div class="col-detail">
					<div class="detail">
						<img src="/img/case.jpg"/>
						<div class="introduce">
							<div class="introduce-detail">圣罗兰口红YL</div>
							<div class="introduce-time">
								<date>2017-12-12</date>
								<date>12:12</date>
							</div>
						</div>
					</div>
				</div>
				<div class="col-detail">
					<div class="detail">
						<img src="/img/case.jpg"/>
						<div class="introduce">
							<div class="introduce-detail">圣罗兰口红YL</div>
							<div class="introduce-time">
								<date>2017-12-12</date>
								<date>12:12</date>
							</div>
						</div>
					</div>
				</div>
				<div class="col-detail">
					<div class="detail">
						<img src="/img/case.jpg"/>
						<div class="introduce">
							<div class="introduce-detail">圣罗兰口红YL</div>
							<div class="introduce-time">
								<date>2017-12-12</date>
								<date>12:12</date>
							</div>
						</div>
					</div>
				</div>
				<div class="col-detail">
					<div class="detail">
						<img src="/img/case.jpg"/>
						<div class="introduce">
							<div class="introduce-detail">圣罗兰口红YL</div>
							<div class="introduce-time">
								<date>2017-12-12</date>
								<date>12:12</date>
							</div>
						</div>
					</div>
				</div>-->
			</div>
		</div>
	</body>
<script>
	$(function () {
        $.ajax({
            //请求类型
            type:"GET",
            //预期服务器返回的数据类型
            dataType:"json",
            //请求URL
            url:"/news/selectNews",
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
                $(".col-detail").remove();
				$.each(data,function (index,value) {
					$div=$("<div class='col-detail' onclick=goToNew('"+value.id+"')>\n" +
                            "<div class='detail'>\n" +
                            "<img src='"+value.img_url+"'/>\n" +
                            "<div class='introduce'>\n" +
                            "<div class='introduce-detail'>"+value.title+"</div>\n" +
                            "<div class='introduce-time'>\n" +
                            "<date>"+getMyDate(value.createTime)+"</date>\n" +
                            "<date>"+getMyTime(value.createTime)+"</date>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>");
					$(".product-type").append($div);
                });
            },
            error:function(data){
				/*alert("请求失败");*/
            }
        });
    });


    /*时间戳转时间*/
    function getMyDate(str) {
        var oDate = new Date(str),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) ;//最后拼接时间
        return oTime;
    }

    function getMyTime(str) {
        var oDate = new Date(str),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oTime = getzf(oHour) + ':' + getzf(oMin);//最后拼接时间
        return oTime;
    }



    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }

    function goToNew(newId) {
		window.location.href="/newsDetails?newId="+newId;
    }
</script>
</html>
