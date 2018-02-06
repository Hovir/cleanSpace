<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>环保空间</title>
    <link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bankCard.css"/>
    <script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="header-footer">
    <div class="header">
        <div class="left">
            <div class="return">
                <a href="">
                    <img src="/img/back-white.png"/>
                </a>
            </div>
        </div>
        <div class=" middle">
            <p>银行卡</p>
        </div>
        <div class=" right">
            <div class="go">
                <a href="">
                    <p>明细</p>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div class="settext bindcard">
        <span>卡号</span>
        <input type="text" name="" id="cardNo" value="" onfocus="clearInfo()"/>
    <#--<input type="text" name="" id="" value="中国工商银行 借记卡" class="cardtype"/>-->
        <select class="cardtype" id="bankDict" onfocus="clearInfo()">
            <option value="0">--请选择银行--</option>
        </select>
    </div>
    <div class="settext">
        <span>持卡人姓名</span>
        <input type="text" name="" id="username" value="" placeholder="请输入银行持卡人姓名" onfocus="clearInfo()"/>
    </div>
    <div class="settext">
        <span>手机号</span>
        <input type="text" name="" id="phone" value="" placeholder="请输入银行预留手机号" onfocus="clearInfo()"/>
    </div>
    <div class="settext">
        <span>验证码</span>
        <input type="text" name="" id="verfiy" value="" placeholder="请输入验证码" class="getnum" onfocus="clearInfo()"/>
        <span class="getnumber">
					<div id="quick1">获取验证码</div>
					<div type="button" id="quick2" style="display: none;">
					    <span id="quick3">60</span>秒
					</div>
				</span>
    </div>
    <div id="info"></div>
    <div class="card-btn" onclick="bindCard()" >
    <#--解除绑定-->
        绑定
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //查询银行
        $.ajax({
            url: "/company/findBankDictAll",
            type: "post",
            dataType: "json",
            success: function (data) {
                $("#bankDict").find("option:gt(0)").remove();
                $.each(data, function (index, value) {
                    var html = "<option value='" + value.id + "'>" + value.bankName + "</option>";
                    //$("#bankDict").css("text-align", "center");
                    $("#bankDict").append(html);
                })
            },
            error: function () {
                alert("请求失败");
            }
        })
    })

    //点击获取验证码
    $("#quick1").click(function () {
        var bankCardNo = $("#cardNo").val(); //银行卡号
        var banksel = $("#bankDict").val(); //选中的银行id
        var phone = $("#phone").val(); //手机号
        var username = $("#username").val();
        if (checkBankNo(bankCardNo)) {
            return;
        }
        if (banksel == 0){
            $("#info").html("请选择银行!").css("color", "red");
            return;
        }
        if ($.trim(phone) == "") {
            $("#info").html("请输入手机号!").css("color", "red");
            return;
        }
        if (!checkPhone(phone)) {
            return;
        }
        if ($.trim(username) == ""){
            $("#info").html("请输入持卡人姓名!").css("color", "red");
            return;
        }
        $("#quick2").show();
        $("#quick1").hide();
        $.ajax({
            url: "/user/getRegisterMessage",
            type: "POST",
            dataType: "json",
            data: {"phone": phone},
            success: function (data) {
                return;
            },
            error: function (data) {
                //alert('获取验证码请求失败!');
            }
        });
        var obj = this;
        var timer = setInterval(function () {
            var tir = $("#quick3");
            var temp = tir.html();
            if (temp > 0) {
                temp = temp - 1;
                tir.html(temp);
            } else {
                $("#quick2").hide();
                $("#quick1").show();
                clearInterval(timer);//关闭定时器
                tir.html(60);
            }
        }, 1000);
    })

    //点击绑定
    function bindCard() {
        var bankCardNo = $("#cardNo").val(); //银行卡号
        var banksel = $("option:selected").val(); //选中的银行id
        var phone = $("#phone").val(); //手机号
        var username = $("#username").val();
        if (checkBankNo(bankCardNo)) {
            return;
        }
        if (banksel == 0){
            $("#info").html("请选择银行!").css("color", "red");
        }
        if ($.trim(phone) == "") {
            $("#info").html("请输入手机号!").css("color", "red");
            return;
        }
        if (!checkPhone(phone)) {
            return;
        }
        if ($.trim(username) == ""){
            $("#info").html("请输入持卡人姓名!").css("color", "red");
            return;
        }
        $.ajax({
            url:"/company/bindBankCard",
            type:"post",
            dataType:"text",
            data:{cardNo:bankCardNo,bankDictId:banksel,phone:phone,username:username},
            success:function (data) {
                if (data == "1"){
                    alert("链接超时，请重新登录!");
                    window.location.href = "/index";
                }
                if (data == "2"){
                    alert("绑定银行卡成功!");
                    window.location.href = "/bankCardPage";
                }
                if (data == "4"){
                    alert("您已绑定银行卡，请先解除绑定!");
                    window.location.href = "/bankCardPage";
                }
                if (data == "3"){
                    alert("绑定失败!");
                    window.location.href = "/bindCardPage";
                }
            },
            error:function (data) {
                alert("请求失败");
            }
        })
    }

    //验证银行卡号
    function checkBankNo(bankno) {
        var bankno = bankno.replace(/\s/g, '');
        if (bankno == "") {
            $("#info").html("请填写银行卡号!").css("color", "red");
            return false;
        }
        if (bankno.length < 16 || bankno.length > 19) {
            $("#info").html("银行卡号长度必须在16到19之间!").css("color", "red");
            return false;
        }
        var num = /^\d*$/;// 全数字
        if (!num.exec(bankno)) {
            $("#info").html("银行卡号必须全为数字!").css("color", "red");
            return false;
        }
        var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
        if (strBin.indexOf(bankno.substring(0, 2)) == -1) {
            $("#info").html("银行卡号开头两位不符合规范!").css("color", "red");
            return false;
        }
    }

    //验证手机号
    function checkPhone(phone) {
        var phoneTest = /^(((13[0-9]{1})|(17[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (!(phoneTest.test(phone))) {
            $("#info").html("手机号输入有误!!").css("color", "red");
            return false;
        } else {
            $("#info").html("");
            return true;
        }
    }

    //清除提示信息
    function clearInfo() {
        $("#info").html("");
    }
</script>
</body>
</html>
