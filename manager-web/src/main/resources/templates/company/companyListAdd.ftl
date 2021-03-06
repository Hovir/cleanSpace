<#--
  Created by IntelliJ IDEA.
  User: 刘洪宇
  Date: 2018/1/19
  Time: 12:08
  To change this template use File | Settings | File Templates.
-->
<#assign path=springMacroRequestContext.getContextPath() />
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="${path}/lib/image/favicon.ico">
    <link rel="Shortcut Icon" href="${path}/lib/image/favicon.ico"/>
<#--[if lt IE 9]-->
    <script type="text/javascript" src="${path}/lib/respond.min.js"></script>
<#--[endif]-->
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="${path}/lib/static/h-ui.admin/css/style.css"/>
<#--[if IE 6]-->
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script><#--[endif]-->
    <title>企业列表-添加</title>
    <link rel="stylesheet" type="text/css" href="${path}/lib/layui/css/layui.css"/>
</head>
<body>

<!--/代码写在这里-->
<article class="page-container">
    <form action="/admin/compListAddData/add/edit"  class="form form-horizontal" id="form-member-add" method="post" enctype="multipart/form-data" autocomplete="off">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入公司名称！" id="title" name="companyName" onfocus="onTitle()">
                <div id="titleError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司Logo：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="but" onmousedown="onImgUrl()">上传图片</button>
                    <div class="layui-upload-list">
                        <div id="img"></div>
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="imgUrl" id="imgUrl"  value="">
            </div>
        </div>
<#--        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司编号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="请输入公司编号！" id="bn" name="bn" onfocus="onBn()">
                <div id="bnError"></div>
            </div>
        </div>-->
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司编号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
                    <select id="bnOne" style="width: 49%;" class="select" size="1" name="bnOne"   onfocus="onBn()" onblur="bnAppend()">
                        <option id="bnOne-k" value="" selected>- 请选编号 -</option>
                    </select>
                    <select id="bnTwo" style="width: 49%;" class="select" size="1" name="bnTwo"   onfocus="onBn()" onblur="bnAppend()">
                        <option id="bnTwo-k" value="" selected>- 请选编号 -</option>
                    </select>
                </span>
                <div id="bnError"></div>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司密码：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="password" class="input-text" value="" placeholder="请输入手机端登录密码！"  id="password" name="password" onfocus="onPassword()">
                <div id="passwordError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>上属公司：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
                    <select id="parentId" class="select" size="1" name="parentId"  onclick="onParentId()" onchange="levelSelectName()">
                        <option id="parentId-k" value="" selected>请选择级别</option>
                        <#--<option value="0"> -上属公司- </option>-->
                    </select>
                </span>
                <div id="parentIdError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司级别：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
                    <select class="select" size="1" name="levelId" id="levelId" onclick="onLevelId()">
                        <option value="" id="levelId-k" selected>请选择级别</option>
                        <#--<option value="0"> - 公司级别 - </option>-->
                    </select>
                </span>
                <div id="levelIdError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司地址：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
                    <select id="pro" style="width: 32.5%;" class="select" size="1" name="state-s" onfocus="onAddress()" onblur="ad_state()">
                        <option id="pro-k" value="" selected>- 省 -</option>
                    </select>
                    <select id="cit" style="width: 33%;" class="select" size="1" name="city-s" onfocus="onAddress()" onblur="ad_city()">
                        <option id="cit-k" value="" selected>- 市 -</option>
                    </select>
                    <select id="dis" style="width: 33%;" class="select" size="1" name="district-s" onfocus="onAddress()" onblur="ad_district()">
                        <option id="dis-k" value="" selected>- 区 -</option>
                    </select>
                </span>
                <div id="addressError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司简介：</label>
            <div class="formControls col-xs-8 col-sm-9" id="editor">
            </div>
            <input type="hidden" name="profile" id="details" value="<p class='profile-p'>公司简介无内容！</p>"/>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <div id="allError"></div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
            <#-- <button onClick="sub(this);" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont">&#xe632;</i> 保存并提交
                </button>-->
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存并提交&nbsp;&nbsp;">
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
                </button>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <div id="save-fail">${(failer)!''}</div>
            </div>
        </div>
        <input type="hidden" class="input-text" value=""  id="bn" name="bn" onfocus="onBn()">
        <input type="hidden" class="input-text" value=""  id="stateValue" name="state"/>
        <input type="hidden" class="input-text" value=""  id="cityValue" name="city"/>
        <input type="hidden" class="input-text" value=""  id="districtValue" name="district"/>
    </form>
</article>
<!--/代码写在这里-->

<!--_footer 作为公共模版分离出去-->
<#include "${path}/manager/_footer.ftl"/>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
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
    //bn
    $bnOne=$("#bnOne");
    $bnTwo=$("#bnTwo");
    bnSelect($bnOne);
    bnSelect($bnTwo);
    function bnSelect(docu) {
        docu.empty();
        var HTML="<option id='' value='' selected>- 请选编号 -</option>";
        for(var i=0;i<10;i++){
            HTML+="<option value=\'"+i+"\'>"+i+"</option>";
        }
        docu.append(HTML);
    }

    //bnAppend();
    function bnAppend() {
       $bnOneVal=$('#bnOne option:selected') .val();
       $bnTwoVal=$('#bnTwo option:selected') .val();
       $bnVal=$bnOneVal+""+$bnTwoVal;
       $bn=$("#bn");
       if($bnOneVal!=""&&$bnTwoVal!=""){
           var url="${path}/company/compListBn/edit";
           data={
               bn:$bnVal
           }
           $.get(url,data,function (req) {
               if(req.bnBoolean){
                   $bn.val("");
                   $("#bnError").html("<span style=\"color: #FF5722;\">公司编号已存在，请重新填写!</span>");
                   $("#allError").html("<span style=\"color: #FF5722;\">公司编号已存在，请重新填写!</span>");
               }else{
                   $bn.val($bnVal);
                   $("#bnError").html("");
                   $("#allError").empty();
               }
           });
       }else{
           $bn.val("");
       }
    }
</script>
<script type="text/javascript">
    //级别名称
    companyName();
    function companyName() {
        var $parentId=$("#parentId");
        $parentId.empty();
        var HTML="<option id='parentId-k' value='0' selected>请选择级别</option>";
        /*var HTML="<option id='parentId-k' value='0' selected>请选择级别</option>" +
                "<option id='parentId-kk' value='1'>-无-</option>";*/
        $.get("${path}/admin/compListAdd/add/companyName.ajax",function (data) {
            if(data!=null){
                $.each($.parseJSON(data),function (index,value) {
                    //JSON数组
                    HTML+="<option value=\'"+value[0]+"\'>"+value[1]+"</option>";
                });
            }
            $parentId.append(HTML);
        });
    }

    function levelSelectName() {
        var $levelSelect=$('#parentId option:selected') .val();//选中的值
        //alert("选中的值="+$levelSelect);
        levelName($levelSelect);
    }
    function levelName(companyId) {
        //alert("companyId="+companyId);
        var $levelId=$("#levelId");
        $levelId.empty();
        var HTML="<option id='levelId-k' value='' selected>请选择级别</option>";
        if(companyId!=0){
            $.get("${path}/admin/compListAdd/add/"+companyId+"/levelName.ajax",function (data) {
                //alert("data"+data);
                if(companyId==1&&data!=null){
                    $.each($.parseJSON(data),function (index,value) {
                        HTML+="<option value=\'"+value[0]+"\'>"+value[1]+"</option>";
                    });
                }
                if(companyId!=1&&data!=null){
                    $.each($.parseJSON(data),function (index,value) {
                        HTML+="<option value=\'"+value.id+"\'>"+value.name+"</option>";
                    });
                }
                $levelId.append(HTML);
            });
        }else {
            $levelId.append(HTML);
        }

    }
</script>
<script>
    //地址
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
            url:"/company/selectAddressDict",
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
                //alert("请求失败");
            }
        });
    }
</script>
<script type="application/javascript">
    //判断
    function sub() {
        $("#titleError").empty();
        $("#demoText").empty();
        //alert("add="+editor.txt.html());
        $("#details").val(editor.txt.html());
        if ($("#title").val() == "") {
            $("#titleError").html("<span style=\"color: #FF5722;\">企业名称不能为空！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">企业名称不能为空！</span>");
            return false;
        } else if ($("#imgUrl").val() == "") {
            $("#demoText").html("<span style=\"color: #FF5722;\">请选择图片！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">请选择图片！</span>");
            return false;
        }else if ($("#bnOne").val() == "") {
            $("#bnError").html("<span style=\"color: #FF5722;\">请输入两位公司编号！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">请输入两位公司编号！</span>");
            return false;
        }else if ($("#bnTwo").val() == "") {
            $("#bnError").html("<span style=\"color: #FF5722;\">请输入两位公司编号！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">请输入两位公司编号！</span>");
            return false;
        }else if ($("#bn").val() == "") {
            $("#bnError").html("<span style=\"color: #FF5722;\">公司编号已存在，请重新填写！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">公司编号已存在，请重新填写！</span>");
            return false;
        }else if ($("#password").val() == "") {
            $("#passwordError").html("<span style=\"color: #FF5722;\">密码不能为空！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">密码不能为空！</span>");
            return false;
        }else  if ($('#parentId option:selected').val()=='0'){
            $("#parentIdError").html("<span style=\"color: #FF5722;\">请选择上属公司！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">请选择上属公司！</span>");
            return false;
        } else  if ($('#levelId option:selected').val()==''){
            $("#levelIdError").html("<span style=\"color: #FF5722;\">请选择公司级别！( *此处根据上属公司级别权限设定！)</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">请选择公司级别！（ *此处根据上属公司级别权限设定！）</span>");
            return false;
        }else  if ($("#pro").val()==''||$('#cit').val()==''||$('#dis').val()==''){
            $("#addressError").html("<span style=\"color: #FF5722;\">请选择公司地址！</span>");
            $("#allError").html("<span style=\"color: #FF5722;\">请选择公司地址！</span>");
            return false;
        }else {
            return true;
        }
    }

    function onTitle() {
        $("#titleError").html("");
        $("#allError").html("");
    }
    function onImgUrl() {
        $("#demoText").empty();
        $("#allError").empty();
    }
    function onBn() {
        $("#bnError").empty();
        $("#allError").empty();
    }
    function onPassword() {
        $("#passwordError").empty();
        $("#allError").empty();
    }
    function onParentId() {
        $("#parentIdError").empty();
        $("#allError").empty();
    }
    function onLevelId() {
        $("#levelIdError").empty();
        $("#allError").empty();
    }
    function ad_state() {
       var stateVal=$("#pro option:selected").html();
       $("#stateValue").val(stateVal);
    }
    function ad_city() {
        var cityVal=$("#cit option:selected").html();
        $("#cityValue").val(cityVal);
    }
    function ad_district() {
        var districtVal=$("#dis option:selected").html();
        $("#districtValue").val(districtVal);
    }
    function onAddress() {
        $("#addressError").empty();
        $("#allError").empty();
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
                if(sub()){
                    alert("提交成功！");
                    layer.msg('已保存!', {icon: 1, time: 1000});
                    $(form).ajaxSubmit();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.$('.btn-refresh').click();
                    parent.layer.close(index);
                }
            }
        });
    });
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>