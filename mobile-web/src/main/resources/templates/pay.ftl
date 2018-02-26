<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/pay.css"/>
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
					<p>激活购买</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="">
							<!--<span>注册</span>-->
							<!--<img src= "img/setting.png" />-->
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content">				
		    <!--<div class="payMoney">
				
                <span class="span_1">实际支付</span>
                <span class="span_2">￥ 299.00</span>
            </div>-->
        <div class="sheZhi">    
        	<form action="/wx/makeOrder" method="post" id="myForm">
        	
            <a id="weixin-href" href="javascript:void(0)" class="si">
            	<span class="span_3">
            		<img src="/img/weChat.png" class="wechatpic">
                    <em class="wechatword">微信支付</span>
            	</span>
            	
                <span class="span_4">               	
                	<input id="weixin-radio"  class="redRadio" type="radio"  name="payment" value="1">
                    <img id="weixin-image" class="redRadio"  src="/img/yq.png" >
                </span>
                
            </a>

            <div class="bottom-footer">
           	    <div class="total">
            	   合计：<span>￥200.00</span>
                </div>
                <span <#--href="javascript:mysubmit()"--> onclick="goToPay()" class="payMoney_confirm" >确认支付</span>
            </div>
                <input type="hidden" value="${cardId!''}" id="cardId" name="cardId">
                <input type="hidden" value="${phone!'123'}" id="phone" name="phone">
                <input type="hidden" value="2" id="money" name="price">

        	</form>
        </div>
            <span style="color: red;text-align: center;padding: 20px;"
                  id="errorMessage">${msg!''}</span>
			</div>



<script type="text/javascript">

    $("#weixin-href").click(function(){
        $("#weixin-image").attr("src","/img/paySucceed.png");
        $("#weixin-radio").attr("checked","checked");

    });


</script>
<script>
	function huiTui () {
		if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
			window.location.href = window.document.referrer;
		} else { window.history.go(-1); };
	}
</script>

<script>
	function goToPay() {
       var a=$("#weixin-radio").prop("checked");
       var phone=$("#phone").val();
       var money=$("#money").val();
       var cardId=$("#cardId").val();
       if(phone=="123"){
           alert("登录超时请重新登录");
           window.location.href="/login";
	   }
	   if($.trim(cardId).length==0){
           alert("数据异常");
           window.location.href="/";
           return;
	   }
        if(!a){
            alert("请选择支付方式！");
            return;
		}
		$("#myForm").submit();
       /*
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"text",
            //请求URL
            url:"/pay/payOk",
            //传入服务器端的参数值
            data:{phone:phone,money:money,cardId:cardId},
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
				alert(data);
            },
            error:function(data){
				alert("请求失败");
            }
        });*/
    }
</script>

	</body>
</html>
