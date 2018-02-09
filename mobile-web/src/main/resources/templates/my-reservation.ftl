<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>环保空间</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/detection and management.css"/>
	    <script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

	</head>
	<script>
//  $(function(){      
//      $(".content li").hide();
//      $(".content li").eq(0).show();
//      $(".top-choose span").click(function(){
//          var i=$(this).index() ;
//          $(".content li").eq(i).show().siblings().hide();
//      });
//      
//      $(".top-choose span").click(function(){
//          $(".top-choose span").eq($(this).index()).addClass("testing").siblings().removeClass("testing");
//      });
//
//  });



</script>
	
	
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="/centerPage">
							<img src="img/back.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>我的预约</p>			
                </div>		 
				<div class=" right">
					<div class="go">
						<a href="">							
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content" style="margin-top: 2.8125rem;">	
		   <ul>
		   	<li>
		   		<div class="Today reservation">
		   	    <div class="Subscribe-List" id="orderList">
		   	    	<#--<div class="list">
		   	    		<div>
		   	    			<span>艰苦撒旦哈</span>
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
		   	    	</div>-->
		   	    </div>		   
		   	</li>
		   	
		   </ul>
		   	    	
		   	    		   
		</div>
	</body>
<script>
	$(function () {
		$.ajax({
			url:"/order/myReservation",
			type:"post",
			dataType:"json",
			success:function (data) {
			    if(data == "1"){
			        window.location.href = "/login"
				}
			    $("#orderList").empty();
				$.each(data,function (index, value) {
				    if (value.type == "1"){
                        var html ="<div class='list'><div><span>"+value.name+"</span>" +
                                "<span>"+value.phone+"</span></div><div calss='address'>"+value.state+value.city+value.district+value.address+"</div>" +
                                "<div><span>预约时间：<em>"+getMyDate(value.appointmentTime)+"</em></span>" +
                                "<span><a href='/order/customerDetails?id="+value.id+"' style='color: white;'>查看详情</a></span></div><span style='font-size: 14px'>预约检测</span></div>"
                    }
                    if (value.type == "2"){
                        var html ="<div class='list'><div><span>"+value.name+"</span>" +
                                "<span>"+value.phone+"</span></div><div calss='address'>"+value.state+value.city+value.district+value.address+"</div>" +
                                "<div><span>预约时间：<em>"+getMyDate(value.appointmentTime)+"</em></span>" +
                                "<span><a href='/order/customerDetails?id="+value.id+"' style='color: white;'>查看详情</a></span></div><span style='font-size: 14px'>预约治理</span></div>"
					}
                    $("#orderList").append(html);
                })
            },error:function () {
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
</script>
</html>


