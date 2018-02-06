<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<#assign base=springMacroRequestContext.contextPath/>
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间_公司预约列表</title>
		<script src="${base}/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${base}/css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/css/detection and management.css"/>
	    <script src="${base}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

	</head>
	<script>
    $(function(){      
        $(".content li").hide();
        $(".content li").eq(0).show();
        $(".top-choose span").click(function(){
            var i=$(this).index() ;
            $(".content li").eq(i).show().siblings().hide();
        });
        
        $(".top-choose span").click(function(){
            $(".top-choose span").eq($(this).index()).addClass("testing").siblings().removeClass("testing");
        });

    });



</script>
	
	
	<body>
		<!--<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="">
							<img src="${base}/img/back.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<!--<p>忘记密码</p>-->
					<!--<div class="top-choose">
						<span class="testing">检测</span>
						<span>治理</span>
					</div>-->
                <!--</div>		 
				<div class=" right">
					<div class="go">
						<a href="">
							<!--<span>注册</span>-->
							<!--<!--<img src= "${base}/img/setting.png" />-->
						<!--</a>
					</div>
				</div>
			</div>
		</div>-->
		<div class="content">	
			<div class="top">
				<div class="header">
					<div class="left">
				        <div class="return">
					        <a href="">
						        <img src="${base}/img/back.png" />
					        </a>
				        </div>
				    </div>					
			    </div>
			    <div class="table">
				    <div class="top-choose">
				        <span class="testing">检测</span>
				        <span>治理</span>
			        </div>
			    </div>
			    <div class=" right">
					<div class="go">
						<a href="javascript:void(0)">
							<span>筛选</span>
							<!--<!--<img src= "${base}/img/setting.png" />-->
						</a>
					</div>
				</div>
			</div>
			
			
		   <ul>
		   	<li>
		   		<div class="Today reservation">
		   	    <div class="Subscribe-List">
		   	    	<div class="list">
		   	    		<div>
		   	    			<span>艰苦撒旦哈</span>
		   	    			<span>15604614102</span>
		   	    		</div>
		   	    		<div calss="address">
		   	    			黑龙江省哈尔滨市 南岗区 学府路4号哈尔滨理工大学
		   	    		</div>
		   	    		<div>
		   	    			<span>预约时间：<em>2018-08-23 16:10</em></span>
		   	    			<span id="reports">上传报告</span>
		   	    		</div>
		   	    	</div>
		   	    	<div class="list">
		   	    		<div>
		   	    			<span>卡萨丁金卡</span>
		   	    			<span>15604614102</span>
		   	    		</div>
		   	    		<div calss="address">
		   	    			黑龙江省哈尔滨市 南岗区 学府路4号哈尔滨理工大学
		   	    		</div>
		   	    		<div>
		   	    			<span>预约时间：<em>2018-08-23 16:10</em></span>
		   	    			<span>查看详情</span>
		   	    		</div>
		   	    	</div>
		   	    	<div class="list">
		   	    		<div>
		   	    			<span>谢娜</span>
		   	    			<span>15604614102</span>
		   	    		</div>
		   	    		<div calss="address">
		   	    			黑龙江省哈尔滨市 南岗区 学府路4号哈尔滨理工大学
		   	    		</div>
		   	    		<div>
		   	    			<span>预约时间：<em>2018-08-23 16:10</em></span>
		   	    			<span>查看详情</span>
		   	    		</div>
		   	    	</div>
		   	    </div>		   
		   	</li>
		   	<li>
		   		<div class="Today reservation">
		   	    <div class="Subscribe-List">
		   	    	<div class="list">
		   	    		<div>
		   	    			<span>谢娜</span>
		   	    			<span>15604614102</span>
		   	    		</div>
		   	    		<div calss="address">
		   	    			黑龙江省哈尔滨市 南岗区 学府路4号哈尔滨理工大学
		   	    		</div>
		   	    		<div>
		   	    			<span>预约时间：<em>2018-08-23 16:10</em></span>
		   	    			<span id="reports">查看详情</span>
		   	    		</div>
		   	    	</div>
		   	    	<div class="list">
		   	    		<div>
		   	    			<span>谢娜</span>
		   	    			<span>15604614102</span>
		   	    		</div>
		   	    		<div calss="address">
		   	    			黑龙江省哈尔滨市 南岗区 学府路4号哈尔滨理工大学
		   	    		</div>
		   	    		<div>
		   	    			<span>预约时间：<em>2018-08-23 16:10</em></span>
		   	    			<span>查看详情</span>
		   	    		</div>
		   	    	</div>
				${orderList}
		   	    </div>		   	    	
		   	</li>
		   </ul>
		   	    	
		   	    		   
		</div>
	<script>
		console.log("${orderList}");
	</script>
	</body>
</html>


