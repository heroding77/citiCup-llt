$(document).ready(function () {
    var password = $("#use-password");
    var QRCode = $("#scan-RQ-code");
    var passwordFrame = $("#password");
    var QRCodeFrame = $("#RQ-code");
    password.on("click", function () {
        activateFrame(1);
    });

    QRCode.on("click", function () {
        activateFrame(2);
    });
});

function activateFrame(mod){
    var password = $("#use-password");
    var QRCode = $("#scan-RQ-code");
    var passwordFrame = $("#password");
    var QRCodeFrame = $("#RQ-code");

    password.removeClass("condition-active");
    QRCode.removeClass("condition-active");
    passwordFrame.removeClass("login-content-active");
    QRCodeFrame.removeClass("login-content-active");
    switch (mod) {
        case 1:{
            password.addClass("condition-active");
            passwordFrame.addClass("login-content-active");
            break;
        }
        case 2:{
            QRCode.addClass("condition-active");
            QRCodeFrame.addClass("login-content-active");
            break;
        }
        default:{
            break;
        }
    }
}