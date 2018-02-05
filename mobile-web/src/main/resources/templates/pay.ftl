<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/pay.css"/>
	</head>
	
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="">
							<img src="img/back-black.png" />
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
        	<form action="" method="">
        	
            <a id="weixin-href" href="javascript:void(0)" class="si">
            	<span class="span_3">
            		<img src="img/weChat.png" class="wechatpic">
                    <em class="wechatword">微信支付</span>
            	</span>
            	
                <span class="span_4">               	
                	<input id="weixin-radio"  class="redRadio" type="radio"  name="payment" value="1">
                    <img id="weixin-image" class="redRadio"  src="img/yq.png" >
                </span>
                
            </a>
           
            <div class="bottom-footer">
           	    <div class="total">
            	   合计：<span>￥200.00</span>
                </div>
                <span href="javascript:mysubmit()"  class="payMoney_confirm" >确认支付</span>
            </div>
            
            
        	</form>
        </div>
                  
			</div>
			
			

<script type="text/javascript">

    $("#weixin-href").click(function(){
        $("#weixin-image").attr("src","img/paySucceed.png");
        $("#weixin-radio").attr("checked","checked");

    });


</script>

	</body>
</html>
