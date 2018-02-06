<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间_用户个人中心</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<!--<link rel="stylesheet" type="text/css" href="css/index.css"/>-->
		<link rel="stylesheet" type="text/css" href="css/center.css"/>

	</head>
	<body>
		<div class="header-footer">
			<div class="footer">
				<div class="part">
					<div class="part-cont">
						<a class="" href="/index">
							<span class="icon"><img src="img/homepage.png"/></span>
							<span class="font">首页</span>
						</a>
					</div>
				</div>
				<div class="part">
					<div class="part-cont">
						<a class="" href="/company/myCompany">
							<span class="icon"><img src="img/business.png"/></span>
							<span class="font">企业</span>
						</a>
					</div>
				</div>
				<div class="part">
					<div class="part-cont">
						<a class="" href="javascript:void(0); ">
							<span class="icon"><img src="img/green-center.png"/></span>
							<span class="font active">个人</span>
						</a>
					</div>
				</div>
				
			</div>
		<div class="content">
			<div class="top">				
			  <form action="">
			    <div class="top_personal_infor">
		    	    <div class="top-img">
		    		    <img src="img/enterprise-banner.jpg" alt="" class="topimg"/>	    
		    		    <div class="headimg">
		    		    	<div class="notification"><img src="img/notification.png" alt="" /></div>
		    			    <span><img src="${Session.userHeadImg!'img/center.png'}" alt="" /></span>
		    			    <span>
		    			    	<p>${Session.userName!''}</p>
		    			    	<p>${Session.userPhone!''}</p>
		    			    </span>
		    		    </div>
		    	    </div>
		        </div>
			  </form>
			  
			    <div class="process">
				    <div class="parts">
				    	<a href="/toMyReservation">
				    		<div class="parts-cont">
						       <span class="process_icon"><img src="img/testing.png"/></span>
						       <span class="process_font">我的预约</span>
					        </div>
				    	</a>				    
				    </div>
				    <div class="parts">
                        <a href="/subscribe">
					    <div class="parts-cont">						
						    <span class="process_icon"><img src="img/governance.png"/></span>
						    <span class="process_font">预约治理</span>
					    </div>
						</a>
				    </div>				   			
			    </div>
			</div>
			<div class="middle-con">
				<a href="/personCenter">
				<div class="middle-part">
					<span class="middle-icon"><img src="img/center-my.png" alt="" /></span>
					<span class="middle-word">个人中心</span>
				</div>
                </a>
				<a href="/changePwd">
				<div class="middle-part">
					<span class="middle-icon"><img src="img/pwd.png" alt="" /></span>
					<span class="middle-word">密码管理</span>
				</div>
                </a>
				<div class="middle-part">
					<span class="middle-icon"><img src="img/update.png" alt="" /></span>
					<span class="middle-word">检测更新</span>
				</div>
			</div>

            <div class="btn" id="outSign">登出</div>
			
		</div>

	</body>
<script>
	$("#outSign").click(function () {
		window.location.href="/user/exitLogon";
    });
</script>
</html>
