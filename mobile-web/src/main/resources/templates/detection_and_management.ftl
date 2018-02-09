<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>环保空间_公司预约列表</title>
<#assign base=springMacroRequestContext.contextPath/>
<#setting classic_compatible=true>
    <script src="${base}/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/css/header-footer.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/css/detection and management.css"/>
    <script src="${base}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>

</head>
<script>
    $(function () {
        $(".content li").hide();
        $(".content li").eq(0).show();
        $(".top-choose span").click(function () {
            var i = $(this).index();
            $(".content li").eq(i).show().siblings().hide();
        });

        $(".top-choose span").click(function () {
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
                    <a href="${base}/company/myCompany">
                        <img src="${base}/img/back.png"/>
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
                <a href="${base}/filter">
                    <span>筛选</span>
                    <!--<!--<img src= "${base}/img/setting.png" />-->
                </a>
            </div>
        </div>
    </div>


    <ul>
        <!--parseJsonString-->
    <#assign jsonString>
    ${orderList}
    </#assign>
    <#assign jsonOrderList=jsonString?eval/>
    <#assign count_type1=0/>
    <#assign count_type2=0/>

        <!-- 检查-->
        <li>
            <div class="Today reservation">
                <div class="Subscribe-List">
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
                        <span id="reports">上传报告</span>
                    </div>
                </div>-->
                <#list jsonOrderList?sort_by("createTime") as order>
                    <#if (order.type==1)>
                        <div class="list">
                            <div>
                                <span>${order.name}</span>
                                <span>${order.phone}</span>
                            </div>
                            <div calss="address">
                            ${order.state} ${order.city} ${order.district} ${order.address}
                            </div>
                            <div>
                                <span>预约时间：<em>${order.appointmentTime?number_to_date?string("yyyy-MM-dd HH:mm")}</em></span>
                                <!-- 上传报告或者查看详情-->
                                <#if (order.status==2)>
                                    <span><a href="/order/customerDetails?id=${order.id}">查看详情</a></span>
                                </#if>
                                <#if (order.status==1)>
                                    <span><a href="${base}/upload/${order.id}">提交报告</a></span>
                                </#if>
                            </div>
                        </div>
                    </#if>
                </#list>
                </div>
            </div>
        </li>
        <li>
            <div class="Today reservation">
                <div class="Subscribe-List">
                <#list jsonOrderList?sort_by("createTime") as order>
                    <#if (order.type==2)>
                        <div class="list">
                            <div>
                                <span>${order.name}</span>
                                <span>${order.phone}</span>
                            </div>
                            <div calss="address">
                            ${order.state} ${order.city} ${order.district} ${order.address}
                            </div>
                            <div>
                                <span>预约时间：<em>${order.appointmentTime?number_to_date?string("yyyy-MM-dd HH:mm")}</em></span>
                                <!-- 上传报告或者查看详情-->
                                <#if (order.status==2)>
                                    <span><a href="/order/customerDetails?id=${order.id}">查看详情</a></span>
                                </#if>
                                <#if (order.status==1)>
                                    <span><a href="${base}/upload/${order.id}">提交报告</a></span>
                                </#if>
                            </div>
                        </div>
                    </#if>
                </#list>
                </div>
            </div>
        </li>


    </ul>
</div>
</body>
</html>


