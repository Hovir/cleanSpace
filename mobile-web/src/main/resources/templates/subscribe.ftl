<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<title>环保空间_预约</title>
        <script src="/js/timejs/jquery.min.js"></script>
        <script src="/js/timejs/jquer_shijian.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/timecss/shijian.css"/>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" href="css/subscribe.css" />
	</head>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
                        <a href="javascript:void(0)"  onclick="huiTui()">
							<img src="img/back-black.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>预约治理</p>
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
			<div class="sub-cont">
				<div class="persion-name">
					<input type="text" placeholder="联系人" id="name"/>
				</div>
				<div class="number">
					<input type="text" placeholder="联系方式" id="phone"/>
				</div>
				<div class="font">地址：</div>
				<div class="address">
					<select name="state" id="pro">
                        <option value="666" selected="selected">----省----</option>
					</select>
					<select name="city" id="cit">
                        <option value="666" selected="selected">----市----</option>
					</select>
					<select name="district" id="dis">
                        <option value="666" selected="selected">----区----</option>
					</select>
				</div>
				<div class="address-detail">
					<input type="text" placeholder="详细地址" id="address"/>
				</div>
				<div class="sub-time">
					<span>预约时间：</span>
					<#--<select name="">
						<option value="">2017-12-12 18:18</option>
					</select>-->
					<input type="text" id="appointmentTime"/>
				</div>
				<div class="font">留言：</div>
				<div class="message">
					<textarea name="" rows="" cols="" id="remark"></textarea>
				</div>
				<div class="btn" id="goToAppoint">预约治理</div>
			</div>
		</div>
		
	</body>
<script>
    $(function () {
        threeMove(1,$("#pro"));
    })
    $("#pro").change(function () {
        $("#dis").find("option:gt(0)").remove();
        var parentId=$("#pro").val();
        threeMove(parentId,$("#cit"));
    });

    $("#cit").change(function () {
        var parentId=$("#cit").val();
        threeMove(parentId,$("#dis"));
    });

    function threeMove(parentId,num){
        num.find("option:gt(0)").remove();
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"json",
            //请求URL
            url:"/addressDict/selectAddressDict",
            //传入服务器端的参数值
            data:{parentId:parentId},
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
             $.each(data,function(index,value){
                    var $option=$("<option value='"+value.id+"'>"+value.name+"</option>");
                    num.append($option);
                });
            },
            error:function(data){
                alert("请求失败");
            }
        });
    }
</script>
	
<script>
	$("#goToAppoint").click(function () {
		var name=$("#name").val();
		var phone=$("#phone").val();
		var pro=$("#pro").val();
        var cit=$("#cit").val();
        var dis=$("#dis").val();
        var address=$("#address").val();
		var appointmentTime=$("#appointmentTime").val();
		var remark=$("#remark").val();
        var phoneTest=/^[1][3,4,5,7,8][0-9]{9}$/;
        if(name == null || $.trim(name).length==0){
            alert("联系人姓名不能为空！");
            return;
		}
		if(!phoneTest.test(phone)){
            alert("请输入正确的手机号码！");
            return;
		}
        if(pro == "666" || $.trim(pro).length==0){
            alert("请选择省份！");
            return;
        }
        if(cit == "666" || $.trim(cit).length==0){
            alert("请选择城市！");
            return;
        }
        if(dis == "666" || $.trim(pro).length==0){
            alert("请选择地区！");
            return;
        }
        if(address == null || $.trim(address).length==0){
            alert("详细地址不能为空！");
            return;
        }
        if(appointmentTime == null || $.trim(appointmentTime).length==0){
            alert("请选择预约时间！");
            return;
        }
        if(remark == null || $.trim(remark).length==0){
            alert("请填写留言！");
            return;
        }
        var state=$("#pro option:selected").text();
        var city=$("#cit option:selected").text();
        var district=$("#dis option:selected").text();
        appointmentTime=appointmentTime+":00";
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"text",
            //请求URL
            url:"/order/addOrder",
            //传入服务器端的参数值
            data:{name:name,phone:phone,state:state,city:city,district:district,address:address,appointmentTime:appointmentTime,remark:remark,type:2},
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
                if(data=="1"){
                    alert("预约成功!");
                    window.location.href="/index";
                }else if(data=="2"){
                    alert("预约失败!");
                }else if(data=="3"){
                    alert("请先登录");
                    window.location.href="/login";
                }
            },
            error:function(data){
                alert("请求失败");
            }
        });
    });
</script>
	
<script>
    $("#appointmentTime").shijian({
        startYear:2018,
        val:2018,
        endYear:2099,
        Hour:true,//是否显示小时
        Minute:true,//是否显分钟
    })
</script>

<script>
    function huiTui () {
        if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else { window.history.go(-1); };
    }
</script>
</html>
