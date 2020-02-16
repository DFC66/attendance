/*
$("#login").on('click',function () {
    /!*let url = '/login';
    let username = $("#username").val();*!/
    let password = hex_md5($("#password").val());
    $("#password").val(password);
    /!*let data = {
        username,
        password
    }
    console.log(data);
    $.post(url,data, function(result) {
        window.location.href="/page/index.html";
    })*!/
})*/
function change(){
    var password = hex_md5($("#password").val());
    $("#password").val(password);
    console.log(password)
}