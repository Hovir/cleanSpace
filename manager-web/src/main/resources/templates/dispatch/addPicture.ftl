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
    <title>demo</title>
<#include "${path}/manager/_meta.ftl"/>
</head>
<body>

<form id="jvForm" action="" method="post" enctype="multipart/form-data">
    <table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
        <tbody>
        <tr>
            <td width="20%" class="pn-flabel pn-flabel-h">
                <span class="pn-frequired">*</span>
                上传商品图片(90x150尺寸):</td>
            <td width="80%" class="pn-fcontent">
                注:该尺寸图片必须为90x150。
            </td>
        </tr>
        <tr>
            <td width="20%" class="pn-flabel pn-flabel-h"></td>
            <td width="80%" class="pn-fcontent">
                <img width="100" height="100" id="allImgUrl"/>
                <input type="hidden" name="imgUrl" id="path"/>
                <input type="file" onchange="uploadPic()" name="pic"/>
            </td>
        </tr>
        </tbody>

        <tbody>
        <tr>
            <td class="pn-fbutton" colspan="2">
                <input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>
<!--/代码写在这里-->

<!--/代码写在这里-->

<!--_footer 作为公共模版分离出去-->
<#include "${path}/manager/_footer.ftl"/>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script>


</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>