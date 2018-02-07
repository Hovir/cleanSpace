<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
		<title>环保空间</title>
        <script src="/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/header-footer.css"/>
		<link rel="stylesheet" type="text/css" href="css/bussiness.css"/>
	</head>
	<body>
		<div class="header-footer">
			<div class="header">
				<div class="left">
					<div class="return">
						<a href="/index">
							<img src="img/back-black.png" />
						</a>
					</div>
				</div>
				<div class=" middle">
					<p>环保商家</p>
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
			<div class="sel">
				<select name="" id="level">
					<option value="111">商家类别</option>
				</select>
                <select name="state" id="pro">
                    <option value="666" selected="selected">---省---</option>
                </select>
                <select name="city" id="cit">
                    <option value="666" selected="selected">---市---</option>
                </select>
                <select name="district" id="dis">
                    <option value="666" selected="selected">---区---</option>
                </select>
			</div>
			<#--<div class="product-type">
				<div class="col-detail">
					<div class="detail">
						<img src="img/case.jpg"/>
						<div class="introduce">
							<div class="introduce-title">环境保护部</div>
							<div class="introduce-detail">
								中华人民共和国环境保护部是2008年，
								设立的正部级部门，为国务院组成部门。
								根据第十一届全国人民代表大会第一次
								会议批准的国务院机构改革方案和
								《国务院关于机构设置的通知》
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="product-type">
				<div class="col-detail">
					<div class="detail">
						<img src="img/case.jpg" />
						<div class="introduce">
							<div class="introduce-title">环境保护部</div>
							<div class="introduce-detail">
								中华人民共和国环境保护部是2008年， 设立的正部级部门，为国务院组成部门。 根据第十一届全国人民代表大会第一次 会议批准的国务院机构改革方案和 《国务院关于机构设置的通知》
							</div>
						</div>
					</div>
				</div>
			</div>-->
		</div>
		
	</body>
<script>
	$(function () {
        showCompanyLevel();
        threeMove(1,$("#pro"));
        selectCompanies();
    });


	function showCompanyLevel() {
        $("#level").find("option:gt(0)").remove();
        $.ajax({
            //请求类型
            type:"GET",
            //预期服务器返回的数据类型
            dataType:"json",
            //请求URL
            url:"/level/selectLevels",
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
                $.each(data,function(index,value){
                    var $option=$("<option value='"+value.id+"'>"+value.name+"</option>");
                    $("#level").append($option);
                });
            },
            error:function(data){
				alert("请求失败....");
            }
        });
    }

    $("#pro").change(function () {
        $("#dis").find("option:gt(0)").remove();
        var parentId=$("#pro").val();
        threeMove(parentId,$("#cit"));
        selectCompanies();
    });

    $("#cit").change(function () {
        var parentId=$("#cit").val();
        threeMove(parentId,$("#dis"));
        selectCompanies();
    });

    $("#dis").change(function () {
        selectCompanies();
    });

    $("#level").change(function () {
        selectCompanies();
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

    function selectCompanies() {
        var levelId=$("#level option:selected").val();
        var state=$("#pro option:selected").text();
        var city=$("#cit option:selected").text();
        var district=$("#dis option:selected").text();
        $.ajax({
            //请求类型
            type:"POST",
            //预期服务器返回的数据类型
            dataType:"json",
            //请求URL
            url:"/company/selectCompanies",
            //传入服务器端的参数值
            data:{levelId:levelId,state:state,city:city,district:district},
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
                $(".product-type").remove();
                $.each(data,function (index,value) {
                    $div=$("<div class='product-type'>\n" +
                            "<div class='col-detail'>\n" +
                            "<div class='detail'>\n" +
                            "<img src='"+value.imgUrl+"'/>\n" +
                            "<div class='introduce'>\n" +
                            "<div class='introduce-title'>"+value.name+"</div>\n" +
                            "<div class='introduce-detail'>\n" +
                            value.profile+
                            "</div>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>");
                    $(".content").append($div);
                });
            },
            error:function(data){
                alert("请求失败");
            }
        });
    }
</script>
</html>
