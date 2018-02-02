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
    <title>资讯添加</title>
    <#include "${path}/manager/_meta.ftl"/>
    <link rel="stylesheet" type="text/css" href="${path}/lib/layui/css/layui.css"/>
</head>
<body>

<!--/代码写在这里-->
<article class="page-container">
    <form class="form form-horizontal" id="form-article-add" action="${path}/news/saveUpdateNews" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章标题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="title" name="title">
                <div id="titleError"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>缩略图：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="but">上传图片</button>
                    <div class="layui-upload-list">
                        <div id="img"></div>
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="imgUrl" id="imgUrl">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章内容：</label>
            <div class="formControls col-xs-8 col-sm-9" id="editor"></div>
            <input type="hidden" name="details" id="details"/>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="sub();" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont">&#xe632;</i> 保存并提交
                </button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
                </button>
            </div>
        </div>
        <input type="hidden" id="id" name="id" value="">
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
    function sub() {

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
<script type="application/javascript">
    /*初始化页面*/
    var json = ${(json)?default("")};
    $("#id").val(json.id);
    $("#title").val(json.title);
    $("#imgUrl").val(json.img_url);
    $("#img").html("<img src='" + json.img_url + "' class='layui-upload-img radius' height='100' width='100'>");
    editor.txt.html(json.details);
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>