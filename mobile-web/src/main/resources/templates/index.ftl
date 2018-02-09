<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>环保空间</title>
<#assign base=springMacroRequestContext.contextPath/>
    <link rel="stylesheet" type="text/css" href="${base}/css/header-footer.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/css/lb.css"/>
    <script src="${base}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${base}/js/lb.js" type="text/javascript" charset="utf-8"></script>
<#--<script src="/js/index.js" type="text/javascript" charset="utf-8"></script>-->
</head>
<body>
<div class="header-footer">
    <div class="header">
        <div class="left">
            <div class="return">
                <a href="">
                    <!--<img src="img/return.png" />-->
                </a>
            </div>
        </div>
        <div class=" middle">
            <p>首页</p>
        </div>
        <div class=" right">
            <div class="go">
                <a href="">
                    <!--<img src="img/setting.png" />-->
                </a>
            </div>
        </div>
    </div>
    <div class="footer">
        <div class="part">
            <div class="part-cont">
                <a class="" href="javascript:void(0)">
                    <span class="icon"><img src="${base}/img/green-homepage.png"/></span>
                    <span class="font active">首页</span>
                </a>
            </div>
        </div>
        <div class="part">
            <div class="part-cont">
                <a class="" href="/company/myCompany">
                    <span class="icon"><img src="${base}/img/business.png"/></span>
                    <span class="font">企业</span>
                </a>
            </div>
        </div>
        <div class="part">
            <div class="part-cont">
                <a class="" href="/centerPage">
                    <span class="icon"><img src="${base}/img/center.png"/></span>
                    <span class="font">个人</span>
                </a>
            </div>
        </div>

    </div>
</div>
<div class="content">
    <div class="banner">
        <ul></ul>
        <ol></ol>
        <i class="left"></i><i class="right"></i>
    </div>
    <div class="modules">
        <div class="module">
            <a href="/news">
                <div class="module-cont">
                    <img src="${base}/img/information.png"/>
                    <div>环保资讯</div>
                </div>
            </a>
        </div>
        <div class="module">
            <a href=""></a>
            <div class="module-cont">
                <img src="${base}/img/service.png"/>
                <div>环保咨询</div>
            </div>
            </a>
        </div>
        <div class="module">
            <a href="/subscribe1">
                <div class="module-cont">
                    <img src="${base}/img/testing.png"/>
                    <div>预约检测</div>
                </div>
            </a>
        </div>
        <div class="module">
            <a href="/bussiness">
                <div class="module-cont">
                    <img src="${base}/img/shop-business.png"/>
                    <div>环保商家</div>
                </div>
            </a>
        </div>
    </div>
    <div class="product-type">
        <div class="product-title">
            <div class="title">集团资讯</div>
            <div class="title-right" onclick="turnToContent()">
                <span class="more">更多</span>
                <span class="more-img"><img src="${base}/img/more.png"/></span>
            </div>
        </div>
        <div class="product-cont">
            <#--<div class="col-detail">
                <div class="detail">
                    <img src="${base}/img/case.jpg"/>
                    <div class="introduce">
                        <div class="introduce-detail">圣罗兰口红YL圣罗兰口红YL圣罗兰口红圣罗兰口红圣罗兰口红YL圣罗兰口红YL圣罗兰口红YL</div>
                        <div class="introduce-time">
                            <date>2017-12-12</date>
                            <date>12:12</date>
                        </div>
                    </div>
                </div>
            </div>-->

        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        $(".col-detail").remove();
        $.ajax({
            //请求类型
            type: "GET",
            //预期服务器返回的数据类型
            dataType: "json",
            //请求URL
            url: "/news/selectContentAjax/5",
            //从ajax异步对象中获取服务器响应的html数据
            success: function (data) {

                $.each(data, function (index, value) {
                    $div = $("<div class='col-detail' onclick=goToNew('" + value.id + "')>\n" +
                            "<div class='detail'>\n" +
                            "<img src='" + value.imgUrl + "'/>\n" +
                            "<div class='introduce'>\n" +
                            "<div class='introduce-detail'>" + value.title + "</div>\n" +
                            "<div class='introduce-time'>\n" +
                            "<date>" + getMyDate(value.createTime) + "</date>\n" +
                            "<date>" + getMyTime(value.createTime) + "</date>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</div>");
                    $(".product-cont").append($div);
                });
            },
            error: function (data) {
                /*alert("请求失败");*/
            }
        });
    });


    /*时间戳转时间*/
    function getMyDate(str) {
        var oDate = new Date(str),
                oYear = oDate.getFullYear(),
                oMonth = oDate.getMonth() + 1,
                oDay = oDate.getDate(),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oSen = oDate.getSeconds(),
                oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay);//最后拼接时间
        return oTime;
    }

    function getMyTime(str) {
        var oDate = new Date(str),
                oHour = oDate.getHours(),
                oMin = oDate.getMinutes(),
                oTime = getzf(oHour) + ':' + getzf(oMin);//最后拼接时间
        return oTime;
    }


    function getzf(num) {
        if (parseInt(num) < 10) {
            num = '0' + num;
        }
        return num;
    }

    //集团资讯详细页
    function goToNew(contentId) {
        window.location.href = "/content?contentId=" + contentId;
    }

    //跳转全部集团资讯列表
    function turnToContent() {
        window.location.href = "/group_content";
    }
</script>
</html>
