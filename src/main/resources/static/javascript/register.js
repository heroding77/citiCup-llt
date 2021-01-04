$(document).ready(function () {

    window.eal_pass = null;   //真实的验证码
    window.button_commit = document.getElementById("submit-btn");
    window.getPass_btn = document.getElementById("getPass-btn");
    getPass_btn.addEventListener("click", temp);
    let $password = $("input[name='password']");
    let $confirm_password = $("input[name='confirm_password']");

    $password.on("change", function () {
        checkLegal($password);
        checkEqual($password, $confirm_password);
    });

    $confirm_password.on("change", function () {
        checkEqual($password, $confirm_password);
    })
});

$(window).resize(function () {
    let $password = $("input[name='password']");
    let $confirm_password = $("input[name='confirm_password']");
    let warn = $("#password-warn");
    setOffset(warn, $($password).offset(), {height : $($password).height(),
        width : $($password).width()});
    let confirm_warn =  $("#confirm-password-warn");
    setOffset(confirm_warn, $($confirm_password).offset(), {height : $($confirm_password).height(),
        width : $($confirm_password).width()});
});

function setOffset(obj, $offset, $rect){
    // let co = {top : $offset.top + $rect.height / 2 - 10, left :  $offset.left + $rect.width};
    $(obj).css("top", $offset.top + $rect.height / 2 - 10);;
    $(obj).css("left", $offset.left + $rect.width);
    // $(obj).offset({top : $offset.top + $rect.height / 2 - 10, left :  $offset.left + $rect.width});
    console.log($(obj).offset());
}

function alertPasswordInfo($offset, $rect, mod){
    let warn = $("#password-warn");
    switch (mod) {
        case 1:     //显示
            setOffset(warn, $offset, $rect);
            warn.fadeIn();
            // warn.addClass("warn-active");
            break;
        case 2:     //隐藏
            // warn.removeClass("warn-active");
            warn.fadeOut();
            break;
    }
}

function alertConfirmPasswordInfo($offset, $rect, mod) {
    let confirm_warn = $("#confirm-password-warn");
    switch (mod) {
        case 1:
            setOffset(confirm_warn, $offset, $rect);
            confirm_warn.fadeIn();
            break;
        case 2:
            confirm_warn.fadeOut();
            break;
    }
}

function checkEqual($password, $confirm_password){
    let pass = $password.val();
    let confirm_pass = $confirm_password.val();
    if (confirm_pass.length === 0){
        return ;
    }
    if (pass !== confirm_pass){
        alertConfirmPasswordInfo($($confirm_password).offset(), {height : $($confirm_password).height(),
            width : $($confirm_password).width()}, 1);
    }
    else{
        alertConfirmPasswordInfo($($confirm_password).offset(), {height : $($confirm_password).height(),
            width : $($confirm_password).width()}, 2);
    }
}

function checkLegal($password){
    let pass = $password.val();
    if (pass.length === 0){
        alertPasswordInfo($($password).offset(), {height : $($password).height(),
            width : $($password).width()}, 2);
    }
    else if ((pass.length > 0 && pass.length < 6) || pass.length > 16){
        alertPasswordInfo($($password).offset(), {height : $($password).height(),
            width : $($password).width()}, 1);
    }
    else{
        alertPasswordInfo($($password).offset(), {height : $($password).height(),
            width : $($password).width()}, 2);
    }
}

const sleep = time => {
    return new Promise(resolve => setTimeout(resolve,time))
};






function changeButtonBackground(button_commit) {
    let button_commit_copy = document.getElementById("submit-btn");
    button_commit_copy.style.backgroundColor = "#000051";
}

function test_pass(){
    if(real_pass == document.getElementById("confirm_code").value){
        button_commit.removeAttribute("disabled");
        button_commit.className = "";
        button_commit.addEventListener("mouseenter", changeButtonBackground);
        alert("验证通过！");
    }else{
        alert("验证码不正确，请重新输入！");
        button_commit.setAttribute("disabled", "disabled");
        button_commit.className = "disable";
        button_commit.removeEventListener("mouseenter", changeButtonBackground);
    }
}


function temp() {
    //获取邮箱地址
    // let email_address = document.getElementById("email-address").value;
    let email_address = $("#email-address").val();
    //邮箱为空  警告
    if(email_address == null){
        alert("邮箱不能为空！");
    }else{     //否则发送邮件

        getPass_btn.setAttribute("style", "cursor: no-drop");
        getPass_btn.removeEventListener("click", temp);
        console.log(getPass_btn.classList);
        sleep(60000).then(()=>{
            getPass_btn.setAttribute("style", "");
            getPass_btn.addEventListener("click", temp);
        });
        //构造params，方便传给后台
        let params = {};
        params.email_add=email_address;
        //ajax的type,url,dataType,contentType,data属性
        $.ajax({
            async : true,
            cache : false,
            type : 'POST',
            url : 'aaaa/bbbb',   //后台收到请求加以处理
            dataType : "json",
            data : params,
            error : function (data) {    //后台返回值就是data  带有result等属性 一般是Map
                alert("Error! Can not send mail. "+data.result)
            },
            success : function (data) {
                alert(data.result);  //弹出正确窗口
                window.real_pass = data.key;   //设置验证码赋值给real_pass
            }
        })
    }
}
