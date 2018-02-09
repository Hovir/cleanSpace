<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>环保空间</title>
    <link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
	<script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="/css/more.css"/>
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
				<p>下级代理</p>
			</div>
			<div class=" right">
				<div class="go">
					<!--<a href="">
						<p>完成</p>
					</a>-->
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<#--<div>
		<div class="pull">
			<div class="left">
				<div class="title">富士康环保店</div>
				<div class="grade">等级:<span>环保蚂蚁</span></div>
			</div>
			<div class="pull-btn">
				
			</div>
		</div>
		<div class="pull-child">
			<div class="left">
				<div class="title">富士康环保店</div>
				<div class="grade">等级:<span>环保蚂蚁</span></div>
			</div>
		</div>
		<div class="pull-child">
			<div class="left">
				<div class="title">富士康环保店</div>
				<div class="grade">等级:<span>环保蚂蚁</span></div>
			</div>
		</div>
        </div>-->
	</div>
	<#--<script src="js/more.js" type="text/javascript" charset="utf-8"></script>-->
</body>
<script>
	$(function () {
        $.ajax({
            //请求类型
            type:"GET",
            //预期服务器返回的数据类型
            dataType:"json",
            //请求URL
            url:"/company/showUnderCompany",
            //从ajax异步对象中获取服务器响应的html数据
            success:function(data){
                $(".content ").html("");
                if(data.flag=="1"){
                    $.each($.parseJSON(data.companies),function (index,value) {
						if(value.level.id==2){
						    var str="";
						    if(value.children==""||value.children==null){
                                str="<div>" +
                                        "<div class='pull'>" +
                                        "<div class='left'>" +
                                        "<div class='title'>"+value.name+"</div>\n" +
                                        "<div class='grade'>等级:<span>"+value.level.name+"</span></div>\n" +
                                        "</div>\n" +
                                        "</div>";
							}else {
                                str="<div>" +
                                        "<div class='pull'>" +
                                        "<div class='left'>" +
                                        "<div class='title'>"+value.name+"</div>\n" +
                                        "<div class='grade'>等级:<span>"+value.level.name+"</span></div>\n" +
                                        "</div>\n" +
                                        "<div class='pull-btn' onclick=showorhide(this,'"+"cld"+index+"')>\n" +
                                        "</div>\n" +
                                        "</div>";
							}
							var index2=index;
                            $.each(value.children,function (index,value) {
                                if(value.status==1){
                                    str+="<div class='pull-child' name='"+"cld"+index2+"' style='display: none'>\n" +
                                            "<div class='left'>\n" +
                                            "<div class='title'>"+value.name+"</div>\n" +
                                            "<div class='grade'>等级:<span>"+value.level.name+"</span></div>\n" +
                                            "</div>\n" +
                                            "</div>";
								}
                            });
                            str+="</div>";
                            var $div=$(str);
                            $(".content ").append($div);
						}else if(value.level.id==3){
						    var $div=$("<div class='pull'>\n" +
                                    "<div class='left'>\n" +
                                    "<div class='title'>"+value.name+"</div>\n" +
                                    "<div class='grade'>等级:<span>"+value.level.name+"</span></div>\n" +
                                    "</div>");
                            $(".content ").append($div);
						}
                    });
				}else if(data.flag=="2"){
					alert("登陆超时，请重新登录！");
					window.location.href="/companyLogin";
				}
            },
            error:function(data){
				/*alert("请求失败...");*/
            }
        });
    });
</script>
<script>
	function showorhide(tag,myname) {
		$("[name="+myname+"]").fadeToggle();
        var image=$(tag).css("background-image");
        var str=image.substring(image.lastIndexOf("/")+1,image.lastIndexOf("."));
		if(str=="add"){
            $(tag).css("background-image","url(img/minus.png)");
        } else{
            $(tag).css("background-image","url(img/add.png)");
        }
    }
</script>
</html>