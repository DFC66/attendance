document.write("<script type='text/javascript' src='../js/configurl.js'></script>");

var refreshMessage = function() {
	   
	var url = getport() + '/student/findByOpenid';
	var personInfoStr = localStorage.getItem('person');
       var personInfo = JSON.parse(personInfoStr);
	 console.log(personInfo.openid)
	if (personInfoStr != null) {
		if(personInfo .openid!=null&&personInfo.openid!=undefined){
			
		   mui.ajax(url, {
			data: {
				openid: personInfo .openid
			},
		
			dataType: 'json', //服务器返回json格式数据
			type: 'post', //HTTP请求类型
			timeout: 10000, //超时时间设置为10秒；
			success: function(data) {
				console.log(JSON.stringify(data));
				if(data.message!=null){
					document.getElementById('stuName').innerHTML = data.message.name;
					document.getElementById('stuNumber').innerHTML = data.message.number;
				}
			},
			error: function(xhr, type, errorThrown) {
				if (type == "timeout") {
					mui.alert("请求超时，请检查网络或重试");
				} else {
					mui.alert("未知网络错误");
				}
				console.log(type);
				console.log(errorThrown);
			}
			
			
		});
		}
		
		var personInfo = JSON.parse(personInfoStr);
		// console.log(typeof personInfo);
		document.getElementById('head-img1').src = personInfo.img;
		document.getElementById('account').innerHTML = personInfo.nicheng;
		document.getElementById('province').innerHTML = personInfo.province;
		document.getElementById('city').innerHTML = personInfo.city;

	} else {
		document.getElementById('head-img1').src = '../imgs/weixin.png';
		document.getElementById('account').innerHTML = '你还未登录';
		document.getElementById('province').innerHTML = '你还未登录';
		document.getElementById('city').innerHTML = '你还未登录';
	}
	
	
}
