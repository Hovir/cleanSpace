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
    <title>企业列表-编辑</title>
<#include "${path}/manager/_meta.ftl"/>
    <link rel="stylesheet" type="text/css" href="${path}/lib/layui/css/layui.css"/>
</head>
<body>

<!--/代码写在这里-->
<article class="page-container">
    <form action="${path}/admin/compListEditData/update/edit" class="form form-horizontal" id="form-article-add"  method="post" enctype="multipart/form-data" autocomplete="off">
        <input type="hidden" id="id" name="id" value="">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>公司名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="title" name="companyName" onfocus="onTitle()">
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
                <input type="hidden" name="imgUrl" id="imgUrl" value="">
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
            <div class="formControls col-xs-8 col-sm-9" id="editort">
            </div>
            <input type="hidden" name="profile" id="details" value=""/>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <div id="allError"></div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;保存并提交&nbsp;&nbsp;">
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
                </button>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <div id="save-fail"><span style="color: #FF5722;">${(fail)!''}</span></div>
            </div>
        </div>
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
<script type="text/javascript" src="${path}/lib/wangeditor3/wangEditor.min.js"></script>
<script type="text/javascript" src="${path}/lib/layui/layui.js"></script>
<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#editort');
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
<script type="text/javascript">
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
<script>
    //地址
    $(function () {
        threeMove(1,$("#pro"));
    })
    $("#pro").change(function () {
        //$("#currentState").remove();
        $("#dis").find("option:gt(0)").remove();
        var parentId=$("#pro").val();

        $("#cit").find("option:gt(0)").remove();
        threeMove(parentId,$("#cit"));
    });

    $("#cit").change(function () {
        var parentId=$("#cit").val();

        $("#dis").find("option:gt(0)").remove();
        threeMove(parentId,$("#dis"));
    });

    function threeMove(parentId,num){
        //num.find("option:gt(0)").remove();
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
    $("#pro").click(function () {
        $("#currentState").remove();
        $("#cit").find("option:gt(0)").remove();
        $("#dis").find("option:gt(0)").remove();
    });
</script>
<script type="text/javascript">
    //验证
    function subb() {
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

        $("#form-article-add").validate({
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
                //alert("subb()="+subb());
                if(subb()){
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
<script type="text/javascript">
    /*初始化页面*/
    $("#id").val("${companyEdit.id!'0'}");
    $("#title").val("${companyEdit.name!''}");
    $("#imgUrl").val("${companyEdit.imgUrl!''}");
    $("#img").html("<img src='${companyEdit.imgUrl!''}' class='layui-upload-img radius' height='100' width='100'>");
    $("#details").val('${(companyEdit.profile)!""}');
    $(".profile-p").before('${(companyEdit.profile)!""}');
    var adress_state="${state!'adressFalse'}";
    var adress_city="${city!'adressFalse'}";
    var adress_district="${district!'adressFalse'}";
    if(adress_state!="adressFalse"&&adress_city!="adressFalse"&&adress_district!="adressFalse"){

        $("#pro option:selected").removeAttr("selected");
        $("#pro").append("<option value='6666' id=currentState selected>${state}</option>");
        $("#stateValue").val("${state}");

        $("#cit option:selected").removeAttr("selected");
        $("#cit").append("<option value='6666' id=currentCity selected>${city}</option>");
        $("#cityValue").val("${city}");

        $("#dis option:selected").removeAttr("selected");
        $("#dis").append("<option value='6666' id=currentDistrict selected>${district}</option>");
        $("#districtValue").val("${district}");
    }
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>