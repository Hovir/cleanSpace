<#--
  Created by IntelliJ IDEA.
  com.godfkc.pojo.User: 刘洪宇
  Date: 2018/1/3
  Time: 17:46
  To change this templates use File | Settings | File Templates.
-->
<!DOCTYPE HTML>
<#assign path=springMacroRequestContext.getContextPath() />
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="favicon.ico" >
    <link rel="Shortcut Icon" href="favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${path}/lib/html5.js"></script>
    <script type="text/javascript" src="${path}/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${path}/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script><![endif]-->
    <!--/meta 作为公共模版分离出去-->

    <title>添加编辑-企业管理</title>
</head>
<body>
<article class="page-container">
    <form class="form form-horizontal" id="form-article-add" action="${path}/news/addNews" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>企业名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="title" name="title">
                <div id="titleError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司图片：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="layui-upload">
                    <span class="btn-upload form-group">
                        <input class="input-text upload-url" type="text" value="" name="uploadfile" id="uploadfile" readonly nullmsg="公司图片" style="width:200px">
                        <a href="javascript:void();" class="btn btn-primary" ><i class="Hui-iconfont" style="width: 100%">&#xe642;</i> 上传图片</a>

                        <input type="hidden" name="imgUrl" id="imgUrl">
                    </span>
                    <div class="layui-upload-list">
                        <div id="img"></div>
                        <p id="demoText"></p>
                    </div>
                    <#--<input type="hidden" name="imgUrl" id="imgUrl">-->
                </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司简介：</label>
            <div class="formControls col-xs-8 col-sm-9" id="editor">
            </div>
            <input type="hidden" name="details" id="details"/>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
<#--                <button onClick="sub(this);" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont">&#xe632;</i> 保存并提交
                </button>-->
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
                </button>
            </div>
        </div>
    </form>
</article>
<script type="application/javascript" src="${path}/lib/wangeditor3/wangEditor.min.js"></script>
<script type="application/javascript" src="${path}/lib/layui/layui.js"></script>
<script type="application/javascript">
    var E = window.wangEditor;
    var editor = new E('#editor');
    // 上传图片到服务器
    editor.customConfig.uploadImgServer = '${path}/news/uploader';
    // 将图片大小限制为 3M
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
    // 限制一次最多上传 5 张图片
    editor.customConfig.uploadImgMaxLength = 5;
    //自定义 fileName
    editor.customConfig.uploadFileName = 'file';
    editor.create();
</script>
<script type="application/javascript">
    layui.use('upload', function () {
        var $ = layui.jquery
                , upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#but'
            , url: '/news/uploader'
            , size: 200
            , exts: 'jpg|png|jpeg'
            //上传成功回调函数 res为服务器返回的json
            , done: function (res) {
                var url = res.data[0];
                $('#img').empty();
                $('#img').html("<img src='" + url + "' class='layui-upload-img radius' height='100' width='100'>");
                $("#imgUrl").val(url);
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    })
</script>
<script type="application/javascript">
    function sub(obj) {

        $("#titleError").empty();
        $("#demoText").empty();

        $("#details").val(editor.txt.html());
        if ($("#title").val() == "") {
            $("#titleError").html("<span style=\"color: #FF5722;\">标题不能为空!</span>");
            return false;
        } else if ($("#imgUrl").val() == "") {
            $("#demoText").html("<span style=\"color: #FF5722;\">请选择图片!</span>");
            return false;
        } else {
            $(obj).parents("tr").remove();
            layer.msg('已保存!', {icon: 1, time: 1000});
            $("#form-article-add").submit();
        }
    }
</script>
<#--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${path}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${path}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${path}/lib/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${path}/lib/static/h-ui.admin/js/H-ui.admin.page.js"></script>
<#--/_footer /作为公共模版分离出去-->

<#--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${path}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${path}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${path}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-member-add").validate({
            rules:{
                username:{
                    required:true,
                    minlength:2,
                    maxlength:16
                },
                sex:{
                    required:true,
                },
                mobile:{
                    required:true,
                    isMobile:true,
                },
                email:{
                    required:true,
                    email:true,
                },
                uploadfile:{
                    required:true,
                },

            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                $(form).ajaxSubmit();
                var index = parent.layer.getFrameIndex(window.name);
                parent.$('.btn-refresh').click();
                parent.layer.close(index);
            }
        });
    });
</script>
<#--/请在上方写此页面业务相关的脚本-->
<script type="text/javascript">
    //照片回显
    function newFile() {
        var windowURL = window.URL || window.webkitURL;
        var loadImg = windowURL.createObjectURL(document.getElementById('upImage').files[0]);
        document.getElementById('showImage').setAttribute('src',loadImg);
    }

    function myImage(){
        var windowURL = window.URL || window.webkitURL;
        var loadImg = windowURL.createObjectURL(document.getElementById("upImage").files[0]);
        if(loadImg!=null){
            var url = $('#showImage').attr("src");
            $("#upImage").val(url);
            alert(url);
            return true;
        }
        return false;
    }
</script>
</body>
</html>
