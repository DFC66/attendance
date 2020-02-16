$('#myModal').modal({backdrop:'static'});

$("#dis").on('click',function () {
    $("#user").css('display','none');
    $("#pass").css('display','block');
})

$("#submit").on('click',function () {
    updatePassword('/updatePassword');
})

//修改密码的ajax
function updatePassword(url) {
    $.post(url,$("#pass").serialize(),function (result) {
        if (result.msg == "修改成功"){
            window.location.reload();
        }
        alert(result.msg);
    })
}
