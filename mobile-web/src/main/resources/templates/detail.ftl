<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间</title>
    <script src="/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
	<link rel="stylesheet" type="text/css" href="/css/detail.css"/>
</head>
<body>
	<div class="header-footer">
		<div class="header">
			<div class="left">
				<div class="return">
					<a href="javascript:void(0)" onclick="huiTui()">
						<img src="/img/back-white.png" />
					</a>
				</div>
			</div>
			<div class=" middle">
				<p>明细</p>
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
	<div class="content" id="detailList">
		<#--<div class="line-cont">
			<div class="line">
				<img src="/img/orange.png" alt="" />
			</div>
			<div class="cont">
				<div class="font">
					<span class="green">提现</span>
					<span>2017-12-12 18:18</span>
				</div>
				<div class="handle">
					<div class="handle-name green">-1.00提现</div>
					<div class="balance">
						<span>余额(元)：</span>
						<span>18888989</span>
					</div>
					<div class="other">
						<span>备注：</span>
						<span>提现成功</span>
					</div>
				</div>
			</div>
		</div>-->
		<#--<div class="line-cont">
			<div class="line">
				<img src="/img/orange.png" alt="" />
			</div>
			<div class="cont">
				<div class="font">
					<span class="red">激活水晶环保检测卡</span>
					<span>2017-12-12 18:18</span>
				</div>
				<div class="handle">
					<div class="handle-name red">+1.00提现</div>
					<div class="balance">
						<span>余额(元)：</span>
						<span>18888989</span>
					</div>
					<div class="other">
						<span>卡号：</span>
						<span>2164196715951515</span>
					</div>
				</div>
			</div>
		</div>-->
	</div>
</body>
<script>
	$(function () {
		$.ajax({
			url:"/company/detailed",
			type:"post",
			dataType:"json",
			success:function (data) {
			    if (data == "1"){
			        alert("连接超时，请重新登录!");
			        window.location.href = "/companyLogin";
				}
				$("#detailList").empty();
			    var html = "";
				$.each(data,function (index, value) {
				    var updateTime = getMyDate(value.updateTime);
				    var money = (value.money/100).toFixed(2);
				    var currentMoney = (value.currentMoney/100).toFixed(2);
					if (value.type == "1"){
					    html = "<div class=\"line-cont\"><div class=\"line\"><img src=\"/img/orange.png\" alt=\"\" /></div>" +
								"<div class=\"cont\"><div class=\"font\"><span class=\"green\">提现</span>" +
								"<span>"+updateTime+"</span></div><div class=\"handle\"><div class=\"handle-name green\">-"+money+"提现</div>" +
								"<div class=\"balance\"><span>余额(元)：</span><span>"+currentMoney+"</span></div><div class=\"other\">" +
								"<span>备注：</span><span>"+value.descreption+"</span></div></div></div></div>";
					}
					if (value.type == "2"){
						html = "<div class=\"line-cont\"><div class=\"line\"><img src=\"/img/orange.png\" alt=\"\" /></div>" +
								"<div class=\"cont\"><div class=\"font\"><span class=\"red\">激活水晶环保检测卡</span>" +
								"<span>"+updateTime+"</span></div><div class=\"handle\"><div class=\"handle-name red\">+"+money+"</div>" +
								"<div class=\"balance\"><span>余额(元)：</span><span>"+currentMoney+"</span></div><div class=\"other\">" +
								"<span>卡号：</span><span>"+value.descreption+"</span></div></div></div></div>";
					}
                    $("#detailList").append(html);
                })
            },
			error:function () {
				//alert("请求失败");
            }
		})
    })
    /*时间戳转时间*/
    function getMyDate(str) {
        var oDate = new Date(str),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
        return oTime;
    }

    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }

    function huiTui () {
        if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else { window.history.go(-1); };
    }
</script>
</html>