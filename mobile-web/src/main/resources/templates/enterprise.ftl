<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>乐美优享</title>
<#assign base=springMacroRequestContext.contextPath/>
    <script src="${base}/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/css/header-footer.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/css/enterprise.css"/>
</head>

<body>
<div class="header-footer">
    <div class="footer">
        <div class="part">
            <div class="part-cont">
                <a class="" href="index.ftl">
                    <span class="icon"><img src="${base}/img/homepage.png"/></span>
                    <span class="font">首页</span>
                </a>
            </div>
        </div>
        <div class="part">
            <div class="part-cont">
                <a class="" href="case.ftl">
                    <span class="icon"><img src="${base}/img/green-business.png"/></span>
                    <span class="font active">企业</span>
                </a>
            </div>
        </div>
        <div class="part">
            <div class="part-cont">
                <a class="" href="">
                    <span class="icon"><img src="${base}/img/center.png"/></span>
                    <span class="font">个人</span>
                </a>
            </div>
        </div>

    </div>
</div>
<!-- jsonString to json-->
<#assign jsonString>
${company!"abc"}
</#assign>
<#assign jsonCompany=jsonString?eval/>

<#assign companyId=jsonCompany.id/>
<div class="content">
    <div class="top-banner">
        <div class="banner">
            <img src="${base}/img/enterprise-banner.jpg" alt=""/>
        </div>
        <div class="topProfit">
            <span class="detail">明细</span>
            <span>今日收益</span>
            <div class="tmoney">
                <span class="money">${jsonCompany.moneyToday}</span>
                <span>元</span>
            </div>
        </div>
    </div>
    <div class="classify">
        <div class="mon-left">
            <div>${jsonCompany.money}</div>
            <div>可用余额(元)</div>
        </div>
        <div class="mon-left">
            <div>${jsonCompany.sumMoney}</div>
            <div>总收益(元)</div>
        </div>
    </div>
    <div>
        <div class="process">
            <div class="parts">
                <a href="">
                    <div class="parts-cont">
                        <span class="process_icon"><img src="${base}/img/Withdrawals.png"/></span>
                        <span class="process_font">现金提现</span>
                    </div>
                </a>
            </div>
            <div class="parts">
                <div class="parts-cont">
                    <span class="process_icon"><img src="${base}/img/bankcard.png"/></span>
                    <span class="process_font">银行卡</span>
                </div>
            </div>
            <div class="parts">
                <div class="parts-cont">
                    <span class="process_icon"><img src="${base}/img/Reservations.png"/></span>
                    <span class="process_font">预约客户</span>
                </div>
            </div>
            <div class="parts">
                <a href="/more">
                <div class="parts-cont">
                    <span class="process_icon"><img src="${base}/img/nextagent.png"/></span>
                    <span class="process_font">下级代理</span>
                </div>
                </a>
            </div>
        </div>
    </div>
    <div class="Today reservation">
        <div class="words">
            <a href="">
                <span>今日预约</span>
                <span><img src="${base}/img/more.png" alt=""/></span>
            </a>
        </div>
        <div class="Subscribe-List">
        <#list jsonCompany.orders?sort_by("createTime") as order>
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
                    <span>查看详情</span>
                </div>
            </div>
        </#list>
        <#--<div class="list">
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
    </div>


</div>
</body>
<script>
    alert(${jsonCompany.money});
    //alert(${jsonCompany.id});
    alert("This is the id"+${companyId})
</script>
</html>


