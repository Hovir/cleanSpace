<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="/css/details of customer.css"/>
	</head>
	<#assign orderType=order.type/>
	<script type="text/javascript">
		  
	</script>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
                        <a href="javascript:void(0)"  onclick="huiTui()">
							<img src="/img/back.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<#if (orderType == 1)>
                        <p>预约检测</p>
					</#if>
					<#if (orderType == 2)>
                        <p>预约治理</p>
					</#if>
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
		    <div class="time">
		    	<div>预约时间：</div>
		    	<div>${order.appointmentTime}</div>
		    </div>
		    <div class="detail">
		    	<dl>
		    	    <dt>联系人：</dt>
		    	    <dd>${order.name}</dd>
		        </dl>
		        <dl>
		    	    <dt>联系电话：</dt>
		    	    <dd>${order.phone}</dd>
		        </dl>
		        <dl>
		    	    <dt>地址：</dt>
		    	    <dd>${order.state} ${order.city} ${order.district}</dd>
		        </dl>
		        <dl>
		    	    <!--<dt></dt>--> 
		    	    <dd>${order.address}</dd>
		        </dl>
		        <dl>
		    	    <dt>留言：</dt>
		    	    <dd>${order.remark}</dd>
		        </dl>
		    </div>
		    
		</div>
	</body>

<script>
	function huiTui () {
		if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
			window.location.href = window.document.referrer;
		} else { window.history.go(-1); };
	}
</script>
</html>
