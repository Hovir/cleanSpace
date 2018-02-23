<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<title>环保空间_预约</title>
        <script src="/js/chooseTime/js/jquery.1.7.2.min.js"></script>
        <script src="/js/chooseTime/js/mobiscroll_002.js" type="text/javascript"></script>
        <script src="/js/chooseTime/js/mobiscroll_004.js" type="text/javascript"></script>
        <link href="/js/chooseTime/css/mobiscroll_002.css" rel="stylesheet" type="text/css">
        <link href="/js/chooseTime/css/mobiscroll.css" rel="stylesheet" type="text/css">
        <script src="/js/chooseTime/js/mobiscroll.js" type="text/javascript"></script>
        <script src="/js/chooseTime/js/mobiscroll_003.js" type="text/javascript"></script>
        <script src="/js/chooseTime/js/mobiscroll_005.js" type="text/javascript"></script>
        <link href="/js/chooseTime/css/mobiscroll_003.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
		<link rel="stylesheet" href="/css/subscribe.css" />
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
					<p>预约检测</p>
				</div>
				<div class=" right">
					<div class="go">
						<a href="javascript:void(0)">
							<!--<img src="img/setting.png" />-->
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="sub-cont">
				<div class="persion-name">
					<input type="text" placeholder="联系人" id="name" maxlength="10"/>
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
                    <input value="" class="" readonly="readonly" name="appDateTime" id="appDateTime" type="text">
				</div>
				<div class="font">留言：</div>
				<div class="message">
					<textarea name="" rows="" cols="" id="remark"></textarea>
				</div>
				<div class="btn" id="goToAppoint">预约检测</div>
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
		var appointmentTime=$("#appDateTime").val();
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
        var date=getMyDate(appointmentTime);
        var d=new Date(Date.parse(date.replace(/-/g,"/")));
        var curDate=new Date();
        if(d <=curDate){
            alert("请选择大于今天的时间");
            return;
        }
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"text",
            //请求URL
            url:"/order/addOrder",
            //传入服务器端的参数值
            data:{name:name,phone:phone,state:state,city:city,district:district,address:address,appointmentTime:appointmentTime,remark:remark,type:1},
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
                }else if(data=="4"){
                    alert("预约次数已达上限，请联系客服");
                }
            },
            error:function(data){
               /* alert("请求失败");*/
            }
        });
    });
</script>

<script type="text/javascript">
    $(function () {
        var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.datetime = {preset : 'datetime'};
        opt.time = {preset : 'time'};
        opt.default = {
            theme: 'android-ics light', //皮肤样式
            display: 'modal', //显示方式
            mode: 'scroller', //日期选择模式
            dateFormat: 'yyyy-mm-dd',
            lang: 'zh',
            showNow: true,
            nowText: "今天",
            startYear: currYear, //开始年份
            endYear: currYear + 10 //结束年份
        };

        $("#appDate").mobiscroll($.extend(opt['date'], opt['default']));
        var optDateTime = $.extend(opt['datetime'], opt['default']);
        var optTime = $.extend(opt['time'], opt['default']);
        $("#appDateTime").mobiscroll(optDateTime).datetime(optDateTime);
        $("#appTime").mobiscroll(optTime).time(optTime);
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
    function getMyDate(str) {
        var oDate = new Date(str),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) ;//最后拼接时间
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
