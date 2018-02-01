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
    <link href="${path}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css"/>
    <style>
        .a-upload {
            padding: 4px 8px;
            /*height: 34px;*/
            line-height: 22px;
            position: relative;
            cursor: pointer;
            color: #fff;
            background-color: #5a98de;
            border-color: #5a98de;
            border-radius: 4px;
            overflow: hidden;
            display: inline-block;
            *display: inline;
            *zoom: 1;
        }

        .a-upload input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
            filter: alpha(opacity=0);
            cursor: pointer
        }

        .a-upload:hover {
            color: #FFFFFF;
            background: #5a98de;
            border-color: #5a98de;
            text-decoration: none;
        }
    </style>
</head>
<body>

<!--/代码写在这里-->
<article class="page-container">
    <form class="form form-horizontal" id="form-article-add" action="${path}/news/addNews" method="post"
          enctype="multipart/form-data" onsubmit="sub()">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章标题：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="" name="title">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>缩略图：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <a href="javascript:;" id="upload" class="a-upload mr10"><input type="file" name="img" id="">点击上传</a>
                <div class="showFileName"></div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章内容：</label>
            <div class="formControls col-xs-8 col-sm-9" id="editor">
            </div>
            <input type="hidden" name="details" id="details"/>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="html();" class="btn btn-primary radius" type="submit"><i
                        class="Hui-iconfont">&#xe632;</i> 保存并提交
                </button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
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
<script type="application/javascript" src="${path}/lib/webuploader/0.1.5/webuploader.min.js"></script>
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
    editor.customConfig.uploadFileName = 'files';
    editor.create();


</script>
<script type="application/javascript">
    //显示隐藏的文件名并上传状态切换
    $('.showFileName').hide();
    $('#uploadBtn').hide();
    $("#upload").on("change", "input[type='file']", function () {
        var filePath = $(this).val();
        if (filePath.indexOf("jpg") != -1 || filePath.indexOf("png") != -1) {
            $(".fileerrorTip").html("").hide();
            var arr = filePath.split('\\');
            var fileName = arr[arr.length - 1];
            $('.showFileName').show();
            $('#uploadBtn').show();
            $(".showFileName").html("已选择文件名：" + fileName);
            $('#upload').hide();
        } else {
            $(".showFileName").html("");
            $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
            return false
        }
    });
</script>
<script type="application/javascript">
    function sub() {
        // 读取 html
        $("#details").val(editor.txt.html());

        return true;
    }
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>