<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>环保空间</title>
    <link rel="stylesheet" type="text/css" href="/css/header-footer.css"/>
    <link rel="stylesheet" type="text/css" href="/css/personCenter.css"/>
    <script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/require.js"></script>
    <script type="text/javascript" src="/js/main.js"></script>
</head>
<style>
    html,body{
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
        overflow: hidden;
    }
    .toBar{
        width: 60%;
        padding: 113px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        position: absolute;
        left: 22%;
        top: 280px;
        z-index: 1;
    }
    .toBar label input{
        display: none;
    }
    .toBar label,.toBar button{
        display: inline-block;
        width: 100px;
        text-align: center;
        padding: 10px 0;
        font-size: 12px;
        color: #fff;
        background: #4db748;
        border-radius: 6px;
        cursor: pointer;
    }
    .toBar button{
        visibility: hidden;
        border: none;
        float: right;
    }
    .img_content,canvas{
        position: absolute;
        top: 240px;
        left: 50%;
        -webkit-transform: translate(-50%,-50%);
        transform: translate(-50%,-50%);
    }
    canvas{
        border: 1px solid #CCCCCC;
    }
    #img-btn{
        display: inline-block;
        width: 60px;
        text-align: center;
        padding: 0 0;
        font-size: 12px;
        color: #fff;
        background: transparent;
        border-radius: 5px;
        cursor: pointer;
        border: 1px solid white;
        border-color: white;
        height: 30px;
        left:90%;
        float: right;
        margin-top: 115%;
        margin-right: 43%;
    }
    #img-input{
        display: none;
    }
</style>
<body style="background-color: rgba(83,83,83,0.77)">
<div class="header-footer">
    <div class="header">
        <div class="left">
            <div class="return">
                <a href="javascript:void(0)" onclick="huiTui()">
                    <img src="/img/back-white.png" />
                </a>
            </div>
        </div>
        <div class=" middle">
            <p>修改头像</p>
        </div>
        <div id="userImage" style="visibility: hidden">
            <p align="right" style="margin-right: 12px;margin-top: 0px"><button type="button" id="img-btn">使用</button></p>
        </div>
    </div>
</div>
    <div class="content">
        <form id="image-sub" action=""  method="post" enctype="multipart/form-data">

            <div class="img_content">
                <img id="myImage"/>
            </div>
            <div>
                <input type="hidden" id="imgStr" name="imgStr"/>
            </div>

            <div class="toBar" id="toBar">
                <label id="mylable">
                    <a style="color: white;text-decoration: none" id="chooseImage">选择图片</a>
                    <input type="file" id="img-input" accept="image/jpeg,image/jpg,image/bmp,image/png"/>
                </label>
            </div>

            <!--裁剪图片框。宽高为定义裁剪出的图片大小-->
            <canvas width="300" height="300" id="myCanvas" style="background-color: rgba(83,83,83,0.77);background-size:100% 100%;background-repeat: no-repeat;background-position:center;border: 1px solid #CCCCCC;background-color: #F5F5F5">
            </canvas>
        </form>
    </div>
<script>
    window.addEventListener("load",function(){
        var $input = document.querySelector("input");
        var $img = document.querySelector("#myImage");
        var $canvas = document.querySelector("#myCanvas");
        //选择图片
        $input.addEventListener("change",function(){
            $img.src = getFileUrl(this);
        },false);

        var myCrop;
        require(["jquery", 'hammer', 'tomPlugin', "tomLib", 'hammer.fake', 'hammer.showtouch'], function($, hammer, plugin, T) {
            document.addEventListener("touchmove", function(e) {
                e.preventDefault();
            });
            var opts = {
                        cropWidth: $canvas.width,
                        cropHeight: $canvas.height
                    },
                    previewStyle = {
                        x: 0,
                        y: 0,
                        scale: 1,
                        rotate: 0,
                        ratio: 1
                    },
                    transform = T.prefixStyle("transform"),
                    myCrop = T.cropImage({
                        bindFile: $("input"),
                        enableRatio: false, //是否启用高清,高清得到的图片会比较大
                        canvas: $canvas, //放一个canvas对象
                        cropWidth: opts.cropWidth, //剪切大小
                        cropHeight: opts.cropHeight,
                        bindPreview: $("#myImage"), //绑定一个预览的img标签
                        useHammer: true, //是否使用hammer手势，否的话将不支持缩放
                        oninit: function() {

                        },
                        onLoad: function(data) {
                            $("#myCanvas").removeAttr("style");
                            $("#img-btn").css("visibility","visible");
                            $("#toBar").hide();
                            $("#editHeadImage").hide();
                            $("#userImage").css("visibility","visible");
                            //用户每次选择图片后执行回调
                            resetUserOpts();
                            previewStyle.ratio = data.ratio;
                            $("#myImage").attr("src", data.originSrc).css({
                                width: data.width,
                                height: data.height
                            }).css(transform, 'scale(' + 1 / previewStyle.ratio + ')');
                            myCrop.setCropStyle(previewStyle)
                        }
                    });

            function resetUserOpts() {
                $("canvas").hammer('reset');
                previewStyle = {
                    scale: 1,
                    x: 0,
                    y: 0,
                    rotate: 0
                };
                $("#myImage").attr("src", '');
            }
            $("canvas").hammer({
                gestureCb: function(o) {
                    //每次缩放拖拽的回调
                    $.extend(previewStyle, o);
                    console.log("用户修改图片", previewStyle);
                    $("#myImage").css(transform, "translate3d(" + previewStyle.x + 'px,' + previewStyle.y + "px,0) rotate(" + previewStyle.rotate + "deg) scale(" + (previewStyle.scale / previewStyle.ratio) + ")")
                }
            });

            $("#img-btn").on("click", function() {
                myCrop.setCropStyle(previewStyle);
                var src = myCrop.getCropFile({});
                $("#imgStr").attr("value",src);
                //$("#image-sub").submit();
                updateImageHeadAjax();
            });
        });


    },false);


    function updateImageHeadAjax() {
        var imgStr = $("#imgStr").val();
        $.ajax({
            url:"/user/userUpdateImageFinish",
            type:"post",
            dataType:"text",
            data:{imgStr:imgStr},
            success:function (data) {
                if (data == "1"){
                    alert("头像修改失败！");
                    window.location.href = "/personCenter";
                }
                if (data == "2"){
                    alert("连接超时，请重新登录！");
                    window.location.href = "/login";
                }
                if (data == "3"){
                    //alert("头像修改成功");
                    window.location.href = "/personCenter";
                }
            },
            error:function () {
                //alert("请求失败");
            }
        })
    }
</script>
<script>
    /*回退按钮*/
    function huiTui () {
        if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else { window.history.go(-1); };
    }
</script>
</body>
</html>