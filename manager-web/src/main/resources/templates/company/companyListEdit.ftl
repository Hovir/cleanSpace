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

    <title>编辑-企业管理</title>
</head>
<style type="text/css">

</style>
<body>
<article class="cl pd-20">
    <div>
        <#--<form action="/user/userUpdateData"  method="post" enctype="multipart/form-data" onsubmit="return myImage()">-->
        <form action="/admin/compListEditData/update/edit" method="post" class="form form-horizontal" id="form-member-add" enctype="multipart/form-data" onsubmit="return myImage()">
            <input type="hidden" id="" name="id" value="${companyEdit.id!'0'}"/>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>企业名称：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="${companyEdit.name!'公司名为空'}" placeholder="" id="username" name="companyName">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">公司图片：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <span class="btn-upload form-group">
                        <input class="input-text upload-url" type="text" name="uploadfile" id="uploadfile" readonly nullmsg="公司图片" style="width:200px">
                        <a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 修改图片</a>
                    <#--<input type="file" multiple name="file-2" class="input-file">-->
                        <input type="file" id="upImage" class="input-file" onchange="newFile()" name="file"  multiple="true" value="${companyEdit.imgUrl!''}"/>
                    </span>
                    <div class="btn-upload form-group" style="margin-top:5px;height: 10%;width: 58%;">
                    <#--<img class="input-text upload-url"  name="uploadfile" id="uploadfile" readonly nullmsg="公司图片！"src="">-->
                        <img id="showImage" name="companyUrl" alt="" src="${companyEdit.imgUrl!''}" />
                    </div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">公司简介：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <textarea   id="profile-area" onkeydown="myArea()" name="beizhu" cols="" rows="" class="textarea"  placeholder="" maxlength="100" onKeyUp="this.value=this.value.substring(0, 100)">${(companyEdit.profile)!"......"}</textarea>
                    <p class="textarea-numberbar"><em class="textarea-length" id="profile-length">0</em>/100</p>
                    <input type="hidden" id="profile-value" name="companyProfile" value="${(companyEdit.profile)!''}"/>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
                    <#--<input class="btn btn-primary radius" type="reset" value="&nbsp;&nbsp;取消&nbsp;&nbsp;">-->
                </div>
            </div>
        </form>
    </div>
</article>

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
            //alert(url);
            return true;
        }
        return false;
    }
</script>
<script type="text/javascript">
    $(function () {
        myArea();
    });
    function myArea() {
        var $profileAreaVal=$("#profile-area").val();
        var $profileAreaLength=$profileAreaVal.length;
        $("#profile-length").html($profileAreaLength);
        $("#profile-value").val($profileAreaVal);
    }
</script>
<#--/请在上方写此页面业务相关的脚本-->
</body>
</html>
