let flag = 0;
$('#myModal').modal({backdrop:'static'});

$("#dis").on('click',function () {
    flag = 3;
    $("#user").css('display','none');
    $("#pass").css('display','block');
})
$("#add,.update").on('click',function () {
    $("#user").css('display','block');
    $("#pass").css('display','none');
})
$("#add").on('click',function () {
    flag = 2;
})

//修改按钮
$(".update").on('click',function () {
    let tr = $(this).parent().parent();
    let id = tr.find("td:eq(1)").text();
    let name = tr.find("td:eq(2)").text();
    let start_time  = tr.find("td:eq(3)").text();
    let end_time = tr.find("td:eq(4)").text();
    let today = tr.find("td:eq(5)").text();
    let room = tr.find("td:eq(6)").text();
    let longitude = tr.find("td:eq(7)").text();
    let dimension = tr.find("td:eq(8)").text();
    let classess = tr.find("td:eq(9)").text();


    $("#user [name='id']").val(id);
    $(".use [name='name']").val(name);
    $(".use [name='start_time']").val(start_time);
    $(".use [name='end_time']").val(end_time);
    $(".use [name='today']").val(today);
    $(".use [name='room']").val(room);
    $(".use [name='longitude']").val(longitude);
    $(".use [name='dimension']").val(dimension);
    $(".use [name='classess']").val(classess);

    flag = 1;
})


//模态框的提交按钮
$("#submit").on('click',function () {
    if(flag == 1){
        update('/course/update');
    }else if(flag == 2){
        add('/course/add');
    }else {
        updatePassword('/updatePassword');
    }
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


//修改的ajax
function update(url){
    $.post(url,$("#user").serialize(),function (result) {
        if (result.code == 200){
            alert(result.msg);
            window.location.reload();
        }else {
            alert("修改课程失败");
            window.location.reload();
        }
    })
    flag = false;
    resetModal();
}

//添加课程的ajax
function add(url) {
    $.post(url,$("#user").serialize(),function (result) {
        if (result.code == 200){
            alert(result.msg);
            window.location.reload();
        }else {
            alert("添加课程失败");
            window.location.reload();
        }
    })
    resetModal();
}

//置空模态框的函数
function resetModal() {
    $(".use [name='name']").val("");
    $(".use [name='start_time']").val("");
    $(".use [name='end_time']").val("");
    $(".use [name='room']").val("");
    $(".use [name='longitude']").val("");
    $(".use [name='dimension']").val("");
    $("#close").click();
}

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
    
    Alldel('/course/deleteMany',ids);
})

function Alldel(url,ids) {
    let data = ids.toString();
    console.log(ids);
    $.post(url,{ids:data},function (result) {
        if (result.code == 200){
            alert(result.msg);
            window.location.reload();
        }else {
            alert("删除课程失败");
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
            url : "/course/delete",
            type : 'get',
            data : {id : Id},
            contentType : 'application/x-www-form-urlencoded', //contentType很重要
            success : function(result) {
                if (result.code == 200){
                    alert(result.msg);
                    window.location.reload();
                }else {
                    alert("删除课程失败");
                    window.location.reload();
                }
            }
        });
    }
});