/**
 * 密码登录
 * 
 */

function login_button(){
	var username=document.getElementById("username").value;//没有.value,则返回[object HTMLInputElement]，一个DOM节点
	var password=document.getElementById("password").value;
	$.ajax({
		url:"/login",
		type:"POST",
		data:JSON.stringify({username:username,password:password}),
		contentType:"application/json;charset=UTF-8",
		dataType:"text",
		success:function (data) {
            var win = window;
			if(data=="user/search"){
                while (win != win.top){//Top属性返回当前窗口的最顶层浏览器窗口。
                    win = win.top;
                }
                win.location.href="/search"
			}else if(data=="admin/login"){
                while (win != win.top){//Top属性返回当前窗口的最顶层浏览器窗口。
                    win = win.top;
                }
                win.location.href="/admin/login.html";
            } else{
//	        	login_button.innerHTML="登录";
                //添加之前，先把这个div删了
                if(document.getElementsByClassName("kk").length>=1){//这里如果只是div!=undefined也不行，因为这里是异步刷新，第一次创建了div,不管后来有没有被删，只要页面不被刷新，这个div就是定义的了
                    $(".kk").remove();
                }
                var div_kk=document.createElement("div");
                div_kk.className="kk";
                div_kk.innerHTML="<em>"+"用户名或密码错误"+"</em>";
                document.getElementById("add").appendChild(div_kk);
            }
		}
	})
};

$(document).ready(function () {
    $('input').focus(function () {
        if(document.getElementsByClassName("kk").length>=1){//这里如果只是div!=undefined也不行，因为这里是异步刷新，第一次创建了div,不管后来有没有被删，只要页面不被刷新，这个div就是定义的了
            $(".kk").remove();
        }
    })
})