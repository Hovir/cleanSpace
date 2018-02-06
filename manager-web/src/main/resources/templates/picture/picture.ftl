<#--
  Created by IntelliJ IDEA.
  User: 刘洪宇
  Date: 2018/1/19
  Time: 12:08
  To change this template use File | Settings | File Templates.
-->
<#assign path=springMacroRequestContext.getContextPath() />
<nav class="breadcrumb"><i class="Hui-iconfont"></i>
    <a href="javascript:;" onclick="pageTurns('manager/_home')" class="maincolor">首页</a>
    <span class="c-999 en">&gt;</span><span class="c-666">图片管理</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;"
       onclick="pageTurns('picture/picture')" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">
        <link rel="stylesheet" href="${path}/lib/dropzone-master/dropzone.css"/>
        <div class="page-container">
            <div class="row cl">
                <label class="col-xs-4 col-sm-2">首页轮播图：<br/><span style="color: #FF5722;">原有图片将被删除!</span></label>
                <div class="col-xs-8 col-sm-9">
                    <form action="${path}/picture/webUploader" method="post" id="dropzone" class="dropzone" enctype="multipart/form-data">
                    </form>
                </div>
            </div>
        </div>
    <#--请在下方写此页面业务相关的脚本-->
        <script type="application/javascript" src="${path}/lib/dropzone-master/dropzone.js"></script>
        <script type="application/javascript">
            $("#dropzone").dropzone({
                uploadMultiple: true,
                parallelUploads:10,
                paramName: "files",
                maxFiles: 3,
                acceptedFiles:"image/*",
                dictMaxFilesExceeded: "您一次最多只能上传{{maxFiles}}个文件",
                dictResponseError: "文件上传失败!",
                dictDefaultMessage: "点击选择文件"
            })
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>