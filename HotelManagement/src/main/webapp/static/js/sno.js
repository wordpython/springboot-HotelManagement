/**
 * 学号登录
 */
function login_button(){
	var account=document.getElementById("mail").value;
	var password=document.getElementById("password").value;
	var regex=/^[0-9A-Za-z]{7,11}$/;
	if(regex.test(mail.value)){
		$.ajax({
			url:"/snoLogin",
			type:"POST",
			data:JSON.stringify({account:account,password:password}),
			contentType:"application/json;charset=UTF-8",
			dataType:"text",
			success:function (data) {
				if(data=="search"){
					var win = window;
					while (win != win.top){//Top属性返回当前窗口的最顶层浏览器窗口。
						win = win.top;
					}
					win.location.href="/search"
				}else{
					layui.use('layer',function(){
						var $ = layui.jquery,layer=layui.layer;
						layer.open({
							title:"信息",
							content:data
						});
					});
				}
			}
		})
	}else{
		layui.use('layer',function(){
			var $ = layui.jquery,layer=layui.layer;
			layer.open({
				title:"提示",
				content:"请输入有效学号"
			})
		});
	}
};

