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
    <span class="c-999 en">&gt;</span><span class="c-666">企业管理</span>
    <span class="c-999 en">&gt;</span><span class="c-666">企业列表</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:;"
       onclick="pageTurns('company/companyList')"  title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a></nav>
<div class="Hui-article">
    <article class="cl pd-20">
        <div style="margin-top: 5%;">
            <div id="search-f" class="text-c">
                日期范围：
                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" name="dateFrom" class="input-text Wdate" style="width:120px;cursor:pointer;"value=""/>
                -
                <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" name="dateTo" class="input-text Wdate" style="width:120px;cursor:pointer;" value="" />
                <input type="text" class="input-text" style="width:250px" placeholder="请输入企业名称" id="companyName" name="companyName" value="">
                <button type="submit" class="btn btn-success radius" id="searchFormBt" name=""><i class="Hui-iconfont">&#xe665;</i> 搜&nbsp;索</button>
                <a href="javascript:;" onclick="member_add('添加企业','${path}/page/company/companyListAdd','1000','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添&nbsp;加</a>
            </div>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort" id="data_tables">
                <thead>
                <tr class="text-c">
                    <th width="50"><input type="checkbox" name="" value=""></th>
                    <th width="300">公司名称</th>
                    <th width="150">公司图片</th>
                    <th width="">公司简介</th>
                    <th width="100">公司级别</th>
                    <th width="130">加入时间</th>
                    <th width="100">操作</th>
                </tr>
                </thead>
                <tbody class="text-c">
                </tbody>
            </table>
        </div>

    <#--请在下方写此页面业务相关的脚本-->
        <script type="application/javascript" src="${path}/lib/datatables/1.10.15/jquery.dataTables.min.js"></script>
        <script type="application/javascript" src="${path}/lib/js/dataTables.js"></script>
        <script type="application/javascript">
            var url = "${path}/admin/compList";
            /*列对应表*/
            var columns = [
                {data: null},
                {data: null},
                {data: null},
                {data: "profile"},
                {data: null},
                {data: null},
                {data: null},
            ];
            /*特殊列渲染*/
            var columnDefs = [
                {name: "id", targets: 0},
                {name: "name", targets: 1},
                {name: "imgUrl", targets: 2},
                {name: "profile", targets: 3},
                {name: "level", targets: 4},
                {name: "createTime", targets: 5},
                {name: "id", targets: 6},
                {
                    //表示第1列
                    targets: 0,
                    //渲染函数
                    render: function (data) {
                        //alert(data);
                        return "<input type=\"checkbox\" name=\"formvalue\" value=\"" + data.id + "\"/>";
                    }
                },{
                    //表示第2列
                    targets: 1,
                    //渲染函数
                    render: function (data) {
                        //alert(data);
                        return data.name;
                    }
                },{
                    //表示第3列
                    targets: 2,
                    //渲染函数
                    render: function (data) {
                        //alert(data);
                        return "<img  name=\'image\' alt=\'公司图片\' src=\'"+data.imgUrl+"\' height=\"25\" width=\"25\"' />";
                    }
                },
                {
                    //表示第4列
                    targets: 4,
                    //渲染函数
                    render: function (data) {
                        //alert("data.level.name="+data.level.name);
                        return data.level.name;
                    }
                },
                {
                    //表示第5列
                    targets: 5,
                    //渲染函数
                    render: function (data) {
                        return getMyDate(data.createTime);
                    }
                },
                {
                    //表示第6列
                    targets: 6,
                    //渲染函数
                    render: function (data) {
                        //alert("data="+data.id);
                        return "<a title=\"详情\" href=\"javascript:;\" onclick=\"member_show('详情',\'${path}/admin/compListShow/"+data.id+"/edit\','10001','400','450')\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe665;</i></a>"+
                                "<a title=\"编辑\" href=\"javascript:;\" onclick=\"member_edit('编辑',\'${path}/admin/compListEdit/"+data.id+"/edit\','4','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>" +
                                "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"change_password('修改密码',\'${path}/admin/compListPwd/"+data.id+"/edit\','10001','600','270')\" href=\"javascript:;\" title=\"修改密码\"><i class=\"Hui-iconfont\">&#xe63f;</i></a>" +
                                "<a title=\"删除\" href=\"javascript:;\" onclick=\"member_del(this,"+data.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                },
                {
                    //禁用排序
                    orderable: false,
                    //指定的列
                    targets: [0, 5],
                }
            ];
            /*初始化dataTables*/
            dataTablesSearch(url, columns, columnDefs);

            /*搜索1*/
            $("#searchFormBt").click(function () {
                var $datefromVal=$("#datemin").val();
                var $datetoVal=$("#datemax").val();
                var $companyNameVal=$("#companyName").val();
                alert("$datefromVal="+$datefromVal);
                alert("$datetoVal="+$datetoVal);
                alert("$companyNameVal="+$companyNameVal);
                if($datefromVal.length!=0&&$datetoVal.length!=0&&$companyNameVal.length!=0) {
                    pageTurnsSearch($datefromVal+"/"+$datetoVal+"/"+$companyNameVal);
                }
            });
            function pageTurnsSearch(url) {
                var data = {
                    url: url
                };
                $.ajax({
                    url: "/admin/compSearch/" + url,    //请求的url地址
                    dataType: "html",   //返回格式为html
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    async: true,    //请求是否异步，默认为异步，这也是ajax重要特性
                    data: data,    //参数值
                    type: "POST",   //请求方式
                    success: function (req) {
                        $("#addHtml").empty();
                        $("#addHtml").html(req);
                    }
                });
            }
        </script>
        <script type="application/javascript">
            /*时间戳转时间*/
            function getMyDate(str) {
                var oDate = new Date(str),
                        oYear = oDate.getFullYear(),
                        oMonth = oDate.getMonth() + 1,
                        oDay = oDate.getDate(),
                        oHour = oDate.getHours(),
                        oMin = oDate.getMinutes(),
                        oSen = oDate.getSeconds(),
                        oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay) + ' ' + getzf(oHour) + ':' + getzf(oMin) + ':' + getzf(oSen);//最后拼接时间
                return oTime;
            }

            function getzf(num) {
                if (parseInt(num) < 10) {
                    num = '0' + num;
                }
                return num;
            }
        </script>
        <script type="text/javascript">
            /*用户-添加*/
            function member_add(title,url,w,h){
                layer_show(title,url,w,h);
            }
            /*用户-查看*/
            function member_show(title,url,id,w,h){
                layer_show(title,url,w,h);
            }
            /*用户-编辑*/
            function member_edit(title,url,id,w,h){
                layer_show(title,url,w,h);
            }
            /*密码-修改*/
            function change_password(title,url,id,w,h){
                layer_show(title,url,w,h);
            }
            /*用户-删除*/
            function member_del(obj,id){
                layer.confirm('确认要删除吗？',function(index){
                    data={
                        id:id,
                    }
                    $.post("${path}/admin/compListStatusData/update/edit.ajax",data,function (data) {
                        if(data.true){
                            layer.msg('已删除!',{icon:1,time:1000});
                            $(obj).parents("tr").remove();
                        }else{
                            layer.msg('删除失败!', {icon: 5, time: 1000});
                        }
                    });
                });
            }

            //复选框选择转换
            function switchAll() {
                var roomids = document.getElementsByName("formvalue");
                for (var j = 0; j < roomids.length; j++) {
                    roomids.item(j).checked = !roomids.item(j).checked;
                }
            }
            //批量删除
            function deleteAll(obj) {
                layer.confirm('确认要删除吗？', function (index) {
                    var id = new Array();
                    var groupCheckbox = $("input[name='formvalue']");
                    for (var i = 0; i < groupCheckbox.length; i++) {
                        if (groupCheckbox[i].checked) {
                            id.push(groupCheckbox[i].value);
                        }
                    }
                    $.post("${path}/admin/compListStatusData/update/edit.ajax", {id: id}, function (req) {
                        if (req) {
                            $(obj).parents("tr").remove();
                            layer.msg('已删除!', {icon: 1, time: 1000});
                            pageTurns("company/companyList");
                        } else {
                            //$(obj).parents("tr").remove();
                            layer.msg('删除失败!', {icon: 5, time: 1000});
                            pageTurns("company/companyList");
                        }
                    })
                })
            }

            //删除
            function del(obj, i) {
                layer.confirm('确认要删除吗？', function (index) {
                    var id = new Array();
                    id.push(i);
                    $.post("${path}/admin/compListStatusData/update/edit.ajax", {id: id}, function (req) {
                        if (req) {
                            $(obj).parents("tr").remove();
                            layer.msg('已删除!', {icon: 1, time: 1000});
                            pageTurns("company/companyList");
                        } else {
                            //$(obj).parents("tr").remove();
                            layer.msg('删除失败!', {icon: 5, time: 1000});
                            pageTurns("company/companyList");
                        }
                    })
                })
            }
        </script>
    <#--请在下方写此页面业务相关的脚本-->

    </article>
<#include "${path}/manager/_copyright.ftl"/>
</div>