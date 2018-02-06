<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
        <script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/bankCard.css"/>
	</head>
	<style>
        .bank_card1 {
            display: inline-block;
            width: 90%;
            height: 7.5rem;
            -webkit-background-size: 100% 100%;
            background-size: 100% 100%;
            margin-top: 1.25rem;
        }
        .bank_card2 {
            position: relative;
            top: 4.6875rem;
            left: 3.75rem;
            font-size: 1.125rem;
            color: #fff;
            font-weight: 600;
        }
	</style>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="">
							<img src="/img/back-white.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>银行卡</p>
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
		<div class="content" id="bankCardList">
			<#--<div class="bank_card">
				<span>0326</span>
			</div>
			<div >
				+ 添加银行卡
			</div>
			<div class="card-btn">
				解除绑定
			</div>-->
		</div>
		
	</body>
<script>
	$(function () {
	    //查询银行卡是否存在
		$.ajax({
			url:"/company/findBandCardByCompanyId",
			type:"post",
			dataType:"json",
			success:function (data) {
			    if(data == "1"){
                    alert("登陆超时，请重新登录！");
                    window.location.href="/companyLogin";
				}
				if (data == "2"){
                    $("#bankCardList").empty();
			        var html = "<div style=\"font-size: 28px;color: rgba(83,83,83,0.77);margin-top: 28px\" onclick='addBankCard()'>+ 添加银行卡</div>";
                    $("#bankCardList").append(html);
				}else{
                    //银行卡后4位
                    var cardNo = data.cardNo.substr(data.cardNo.length-4);
                    var html = "";
                    if (data.bankDict.id == "1"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard1.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "5"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard5.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "6"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard6.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "7"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard7.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "8"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard8.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "9"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard9.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "10"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard10.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "11"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard11.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "12"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard12.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "13"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard13.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "14"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard14.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "15"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard15.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "16"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard16.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "17"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard17.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "18"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard18.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    if (data.bankDict.id == "19"){
                        $("#bankCardList").empty();
                        html = "<div class='bank_card1' style='background-image: url(../img/mycard19.png)'><span class='bank_card2'>"+cardNo+"</span></div><div class=\"card-btn\" onclick='unbindMod("+data.id+")'>解除绑定</div>";
                    }
                    $("#bankCardList").append(html);
				}
            },
			error:function (data) {
				//alert("请求失败");
            }
		})
    })

	function unbindMod(id){
		$.ajax({
			url:"/company/unbindMod",
			type:"post",
			dataType:"text",
			data:{id:id},
			success:function (data) {
				if(data == "success"){
				    alert("解绑成功");
				    window.location.href = "/bankCardPage";
				}else{
				    alert("解绑失败");
                    window.location.href = "/bankCardPage";
				}
            },
			error:function () {
				alert("请求失败");
            }
		})
	}
	
	//绑定银行卡
	function addBankCard() {
		window.location.href = "/bindCardPage";
    }
</script>
</html>
