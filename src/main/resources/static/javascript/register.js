$(document).ready(function () {
    var password = $("#password");
    var confirmPassword = $("#confirm-password");
    var email = $("#email-address");
    var confirmCode = $("#confirm_code");

    email.on("change", function () {
        emailFormatVerification(email.val());
    });

    password.on("change", function () {
        checkLegal(password.val());
    });

    confirmPassword.on("change", function () {
        checkEqual(password.val(), confirmPassword.val());
    });

    confirmCode.on("change", function () {
        checkConfirmCode(confirmCode.val());
    });

    var identifiedCodeBtn = $("#getPass-btn");

    identifiedCodeBtn.on("click", function () {
        var email_value = email.val();

        var time = 30;

        if(email_value !== ""){
            var timerH = setInterval(function () {
                if (time === 0) {
                    identifiedCodeBtn.html("重新获取验证码");
                    clearInterval(timerH);
                } else {
                    identifiedCodeBtn.html("(" + time + ")s");
                    time--;
                }
            }, 1000);

            // 构造params，方便传给后台
                var params = {};
                params.email_add=email_value;
                //ajax的type,url,dataType,contentType,data属性
                $.ajax({
                    async : false,
                    cache : false,
                    type : 'POST',
                    url : 'mail/sendmail',   //后台收到请求加以处理
                    dataType : "json",
                    data : params,
                    error : function (data) {    //后台返回值就是data  带有result等属性 一般是Map
                        alert("Error! Can not send mail. "+data.result)
                    },
                    success : function (data) {
                            window.real_pass = data.key;   //设置验证码赋值给real_pass
                    }
                })

        }
    });
});

function checkEqual(password, confirm_password){
    if (confirm_password.length === 0){
        toast("确认密码错误", "确认密码不能为空", "notice");
    }
    if (password !== confirm_password){
        toast("确认密码错误", "与设定密码不符", "error");
    }
    else{
        toast("确认密码正确", "符合要求", "success");
        return true;
    }
    return false;
}

function checkLegal(password){
    if (password.length === 0){
        toast("密码错误", "密码不能为空", "notice");
    }
    else if ((password.length > 0 && password.length < 6) || password.length > 16){
        toast("密码错误", "密码过长或过短", "error");
    }
    else{
        toast("密码无误", "密码格式符合要求", "success");
        return true;
    }
    return false;
}

function emailFormatVerification(val) {
    if (val === null || val === "" || val === undefined) {
        toast("邮箱错误", "邮箱不能为空", "notice");
        return false;
    }
    var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var domains = ["qq.com", "163.com", "vip.163.com", "263.net", "yeah.net", "sohu.com", "sina.cn", "sina.com", "eyou.com", "gmail.com", "hotmail.com", "42du.cn"];
    if (pattern.test(val)) {
        var domain = val.substring(val.indexOf("@") + 1);
        for (var i = 0; i < domains.length; i++) {
            if (domain === domains[i]) {
                toast("邮箱无误", "邮箱格式符合要求", "success");
                return true;
            }
        }
    }
    toast("邮箱错误", "邮箱格式错误", "error");
    return false;
}

function checkConfirmCode(confirmCode){
    if (window.real_pass === undefined || window.real_pass === "" || window.real_pass === null){
        toast("验证码提醒", "您还没有请求验证码呢！", "notice");
        return false;
    }
    else {
        if (confirmCode === undefined || confirmCode === "" || confirmCode === null) {
            toast("验证码错误", "验证码不能为空", "error");
        }
        else if (confirmCode.toLowerCase() !== window.real_pass.toLowerCase()){
            toast("验证码错误", "验证码不匹配", "error");
        }
        else{
            toast("验证码正确", "验证码匹配", "success");
            return true;
        }
    }
    return false;
}

// error info success warning notice
function toast(title, message, type){
    $.Toast(title, message, type, {
        stack: true,
        has_icon:true,
        has_close_btn:true,
        fullscreen:false,
        timeout:2000,
        sticky:false,
        has_progress:true,
        rtl:false,
    });
}

function submitCheck(){
    var password = $("#password");
    var confirmPassword = $("#confirm-password");
    var email = $("#email-address");
    var username = $("#username");
    var confirmCode = $("#confirm_code");

    if (username.val() === "" || username.val() === undefined || username.val() === null){
        toast("用户名错误", "用户名不能为空" ,"notice");
        return false;
    }

    return emailFormatVerification(email.val()) && checkLegal(password.val())
        && checkEqual(password.val(), confirmPassword.val()) && checkConfirmCode(confirmCode.val());
}