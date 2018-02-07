/*UTF-8*/

//提交
$(document).ready(function () {
    $("#submit-login").click(function () {
        if(myInputName()&&myInputPwd()){
            $("#form-login").attr("method","post");
            $("#form-login").attr("action","/admin/admin/doLogin");
            $("#form-login").submit();
        }else{
            var $failMessage=$("#fail_message");
            $failMessage.html("请您正确输入登录信息！");
        }
    });
});

//Admin用户名
function myInputName(){
    var $adminName=$("#adminInputName");
    var $adminNameVal=$("#adminInputName").val();
    if($adminNameVal.length==0){
        $adminName.css("color","red");
        return false;
    }else if($adminNameVal.length!=0){
        $adminName.css("color","green");
        return true;
    }
}


//Admin用户密码
function myInputPwd() {
    var $adminPwd=$("#adminInputPwd");
    var $adminPwdVal=$("#adminInputPwd").val();
    if($adminPwdVal.length==0){
        $adminPwd.css("color","red");
        return false;
    }else if($adminPwdVal.length!=0){
        $adminPwd.css("color","green");
        return true;
    }
}

//清空提示信息
function myEmpty() {
    $failMessage=$("#fail_message");
    $failMessage.html("");
}