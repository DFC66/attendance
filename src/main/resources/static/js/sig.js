//全选
$("#selectAll").on('change',function () {
    if($(this).is(':checked')){
        $(".sel").prop("checked",true);
    }else{
        $(".sel").prop("checked",false);
    }
})

$(".sel").on('change',function () {
    if(!$(this).is(':checked')){
        $("#selectAll").prop("checked",false);
    }
})

//批量删除
$("#allDel").on('click',function () {
    let ids = new Array();
    $(".sel").each(function () {
        if($(this).is(":checked")){
            console.log($(this).parent().next().text());
            let id = $(this).parent().next().text();
            ids.push(id);
        }
    })

    Alldel('/signin/deleteMany',ids);
})

function Alldel(url,ids) {
    let data = ids.toString();
    console.log(ids);
    $.post(url,{ids:data},function (result) {
        if (result.code == 200){
            alert(result.msg);
            window.location.reload();
        }else {
            alert("删除失败");
            window.location.reload();
        }
    })
}

//单个删除
$(document).on("click", "#delete", function() {
    //1、弹出是否确认删除对话框
    var Name = $(this).parents("tr").find("td:eq(2)").text();
    var Id = $(this).parents("tr").find("td:eq(1)").text();
    if (confirm("确认删除【" + Name + "】吗？")) {
        $.ajax({
            url : "/signin/delete",
            type : 'get',
            data : {id : Id},
            contentType : 'application/x-www-form-urlencoded', //contentType很重要
            success : function(result) {
                if (result.code == 200){
                    alert(result.msg);
                    window.location.reload();
                }else {
                    alert("删除失败");
                    window.location.reload();
                }
            }
        });
    }
});

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
