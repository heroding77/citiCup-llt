$(document).ready(function () {
    var password = $("#use-password");
    var QRCode = $("#scan-RQ-code");

    password.on("click", function () {
        activateFrame(1);
    });

    QRCode.on("click", function () {
        activateFrame(2);
    });

    password = $("#password-input");
    console.log(password)
    password.on("change", function () {
        checkLegal(password.val());
    });
});

function activateFrame(mod) {
    var password = $("#use-password");
    var QRCode = $("#scan-RQ-code");
    var passwordFrame = $("#password");
    var QRCodeFrame = $("#RQ-code");

    password.removeClass("condition-active");
    QRCode.removeClass("condition-active");
    passwordFrame.removeClass("login-content-active");
    QRCodeFrame.removeClass("login-content-active");
    switch (mod) {
        case 1: {
            password.addClass("condition-active");
            passwordFrame.addClass("login-content-active");
            break;
        }
        case 2: {
            QRCode.addClass("condition-active");
            QRCodeFrame.addClass("login-content-active");
            break;
        }
        default: {
            break;
        }
    }
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

function checkLegal(password) {
    if (password.length === 0) {
        toast("密码错误", "密码不能为空", "notice");
    } else if ((password.length > 0 && password.length < 6) || password.length > 16) {
        toast("密码错误", "密码过长或过短", "error");
    } else {
        toast("密码无误", "密码格式符合要求", "success");
        return true;
    }
    return false;
}


function submitCheck() {
    var username = $("#username");

    if (username.val() === "" || username.val() === null || username.val() === undefined){
        toast("用户名错误", "用户名不能为空" ,"notice");
        return false;
    }

    var password = $("#password-input");

    return checkLegal(password.val());

}