<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="/js/jquery.min.js"></script>
</head>
<body>
<form id="fwxPay" method="post" action="/wx/checkPay">
    <input type="hidden" name="b_id" value="${b_id}"/>
    <input type="hidden" name="bm_id" value="${bm_id}"/>
    <input type="hidden" name="s_no" value="${s_no}"/>
    <input type="hidden" name="sign" value="${sign}"/>
</form>

<script type="text/javascript">
    $(document).ready(function () {
        if(confirm("是否已完成支付?")){
            $("#fwxPay").submit();
        }else {
            window.location.href = "/";
        }
    })
</script>
</body>
</html>
