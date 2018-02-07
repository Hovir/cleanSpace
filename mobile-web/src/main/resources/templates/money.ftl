<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
	<#setting classic_compatible=true>
        <script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/bankCard.css"/>
	</head>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="/company/myCompany">
							<img src="/img/back-white.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>银行卡</p>
				</div>
				<div class=" right">
					<div class="go">
						<#--<a href="">
							<p>明细</p>
						</a>-->
					</div>
				</div>
			</div>
		</div>

		<div class="content">
			
			<div class="card_font">
				账户余额
				<span>${money}</span>
				元
			</div>
			<div class="no-card" style="display: none;">
				<div class="settext">
					<div class="card">
						<div class="detail null" onclick="addBankCard()">
							<div>添加银行卡</div>
							<img src="/img/more.png"/>
						</div>
					</div>
				</div>
			</div>
			<div class="have-card time" >
				<div class="settext">
					<div class="card">
						<#--<img src="/img/case.jpg"/>-->
							<img src="${companyImg}">
						<div class="detail">
							<div name="bankName"></div>
							<div name="cardNo"></div>

						</div>
						<#--<div class="right">
							<img src="img/more.png"/>
						</div>-->
					</div>
				</div>
			</div>
			<div class="settext time">
				<span>到账时间</span>
				<input type="text"  value="预计3个工作日后" readonly="readonly" />
			</div>
			<div class="settext time">
				<span>提现金额</span>
				<input type="text" name="withdrawlMoney" id="withdrawlMoney" value="" placeholder="请输入金额" />
			</div>
			<div class="card-btn time" onclick="confirmWithdrawals()">
				确认提现
			</div>
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
                    //console.log(data);
                    if(data == "1"){
                        alert("登陆超时，请重新登录！");
                        window.location.href="/companyLogin";
                    }
                    if (data == "2"){
                        $(".no-card").show();
                        $(".time").hide();
                    }else{
                        //银行卡后4位
                        var cardNo_last3 = data.cardNo.substr(data.cardNo.length-3);
                        var cardNo_first4= data.cardNo.substr(0,4);
                        $("[name='cardNo']").html(cardNo_first4+"******"+cardNo_last3);
                        $("[name='bankName']").html(data.bankDict.bankName);
                    }
                },
                error:function (data) {
                    //alert("请求失败");
                }
            })
        })

		//点击确认提现
		function confirmWithdrawals() {
			var withdrawlMoney = $("#withdrawlMoney").val();
            var regu = "^([1-9]\\d{0,9}|0)([.]?|(\\.\\d{1,2})?)$";
            var re = new RegExp(regu);
            if(!re.test(withdrawlMoney)){
                alert("请您正确输入提现金额！")
                return;
            }
            withdrawlMoney = withdrawlMoney*100;
			$.ajax({
				url:"/company/confirmWithdrawals",
				type:"post",
				dataType:"text",
				data:{withdrawlMoney:withdrawlMoney},
				success:function (data) {
					if (data == "1"){
					    alert("连接超时,请重新登录！")
					    window.location.href = "/companyLogin";
					}
					if (data == "2"){
					    alert("对不起,您的余额不足!");
					}
					if (data == "3"){
					    alert("未知错误,提现失败！");
                        window.location.href = "/company/myCompany";
					}
					if (data == "4"){
					    alert("提现成功!");
					    window.location.href = "/company/myCompany";
					}

                },
				error:function () {
					//alert("请求失败")
                }
			})
        }

        //绑定银行卡
        function addBankCard() {
            window.location.href = "/bindCardPage";
        }
	</script>
</html>
