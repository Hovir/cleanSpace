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
    <form class="form form-horizontal" id="form-article-add" action="${path}/dispatch/addPicture" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>上传报告：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="but">上传图片</button>
                    <div class="layui-upload-list">
                        <div id="img"></div>
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="report" id="imgUrl">
                <input type="hidden" name="id" id="orderId" value="${id}">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="sub(this);" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont">&#xe632;</i> 保存并提交
                </button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;返回&nbsp;&nbsp;
                </button>
            </div>
        </div>
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
    // 限制一次最多上传 1 张图片
    editor.customConfig.uploadImgMaxLength = 1;
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
                $('#img').html("<img src='" + url + "' class='layui-upload-img radius' height='640' width='360'>");
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

       if ($("#imgUrl").val() == "") {
            $("#demoText").html("<span style=\"color: #FF5722;\">请选择要上传报告!</span>");
            return false;
        } else {
            $(obj).parents("tr").remove();
           layer.msg('已保存!', {icon: 1, time: 1000});
            $("#form-article-add").submit();
        }
    }
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>