<#assign path=springMacroRequestContext.getContextPath() />
<nav class="breadcrumb"><i class="Hui-iconfont"></i> <a href="javascript:;" onclick="pageTurns('manager/_home')"
                                                         class="maincolor">首页</a>
    <span class="c-999 en">&gt;</span>
    <span class="c-666">我的桌面</span>
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:void (0);"
       onclick="pageTurns('manager/_home')" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="Hui-article">
    <article class="cl pd-20">
        <p class="f-20 text-success">欢迎使用<span class="f-14">v1.0</span>后台！</p>
        <table class="table table-border table-bordered table-bg">
            <thead>
            <tr>
                <th colspan="7" scope="col">信息统计</th>
            </tr>
            <tr class="text-c">
                <th>统计</th>
                <th>资讯库</th>
                <th>公司库</th>
                <th>首页广告库</th>
                <th>订单库</th>
                <th>卡片库</th>
            </tr>
            </thead>
            <tbody>
            <tr class="text-c">
                <td>总数</td>
                <td id="news">0</td>
                <td id="company">0</td>
                <td id="headPageImg">0</td>
                <td id="order">0</td>
                <td id="card">0</td>
            </tr>
            </tbody>
        </table>
        <table class="table table-border table-bordered table-bg mt-20">
            <thead>
            <tr>
                <th colspan="2" scope="col">服务器信息</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="30%">服务器计算机名</th>
                <td><span id="lbServerName">阿里云服务</span></td>
            </tr>
            <tr>
                <td>服务器IP地址</td>
                <td id="ip">0</td>
            </tr>
            <tr>
                <td>Java版本</td>
                <td id="version">0</td>
            </tr>
            <tr>
                <td>java安装目录</td>
                <td id="javaHome">0</td>
            </tr>
            <tr>
                <td>服务器系统</td>
                <td id="osName">0</td>
            </tr>
            <tr>
                <td>服务器架构</td>
                <td id="osArch">0</td>
            </tr>
            <tr>
                <td>服务器系统版本</td>
                <td id="osVersion">0</td>
            </tr>
            <tr>
                <td>服务器的语言种类</td>
                <td>English</td>
            </tr>
            <tr>
                <td>服务器时间</td>
                <td id="time"></td>
            </tr>
            <tr>
                <td>服务器cpu个数</td>
                <td id="cpu">0</td>
            </tr>
            <tr>
                <td>虚拟内存总量</td>
                <td id="ramAll">0</td>
            </tr>
            <tr>
                <td>虚拟内存剩余量</td>
                <td id="ramleLsure">0</td>
            </tr>
            </tbody>
        </table>
    </article>
<#include "${path}/manager/_copyright.ftl"/>
</div>
<script type="application/javascript">
    $.get("${path}/home/message", function (req) {
        $("#news").text(req.news);
        $("#company").text(req.company);
        $("#headPageImg").text(req.headPageImg);
        $("#card").text(req.card);
        $("#order").text(req.order);
        $("#version").text(req.version);
        $("#vendor").text(req.vendor);
        $("#javaHome").text(req.javaHome);
        $("#osName").text(req.osName);
        $("#osArch").text(req.osArch);
        $("#osVersion").text(req.osVersion);
        $("#ip").text(req.ip);
        $("#cpu").text(req.cpu);
        $("#ramAll").text(req.ramAll);
        $("#ramleLsure").text(req.ramleLsure);
        $("#time").text(req.time);
    }, "json")
</script>