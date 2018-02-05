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
    <span class="c-999 en">&gt;</span><span class="c-666">修改佣金</span>
   <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;" onclick="pageTurns('examine/cassification')" title="刷新" >
       <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">

        <form class="form form-horizontal" id="form1" action="/updataCommision">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>佣金：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="${map.commision}" onselect="selected" placeholder="" id="commision" name="commision">
                    <input type="hidden" id="id" name="id" value="${map.id}">
                    <div id="titleError"></div>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button onclick="sub();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i> 保存并提交
                    </button>

                </div>
            </div>
        </form>

    <#--请在下方写此页面业务相关的脚本-->
        <script type="text/javascript" src="${path}/lib/jquery/jquery-3.2.1.js"></script>
        <script type="text/javascript">
            function sub() {

                if ($("#commision").val() == "") {
                    alert("佣金不能为空!");
                    return false;
                }
                if(confirm("是否修改")){
                    $("#form1").submit();
                }

            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
    <#include "${path}/manager/_copyright.ftl"/>
</div>

