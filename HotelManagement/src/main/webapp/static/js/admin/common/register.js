/**
 * 
 */

/*得到焦点是给出提示信息，失去焦点时进行判断是否合法，并给相应的提示或将提示clear*/
//得到焦点时，提示会员名的要求
function username_onfocus(){
	clearContent("kk");
	var div_kk=document.createElement("div");
	div_kk.id="kk";
	div_kk.innerHTML="<ul><li>会员名为5-25字符，只能包含字母和数字或汉字</li><li>请勿包含身份证/银行卡等隐私信息，一旦设置成功无法修改</li></ul>";
	document.getElementById("add").appendChild(div_kk);
	div_kk.style.setProperty("border","1px solid #d4d4d4");
	div_kk.style.setProperty("text-align","left");
	div_kk.style.setProperty("color","#73777a");
	div_kk.style.setProperty("border-radius","16px");
	div_kk.style.setProperty("margin-top","-14px");
	div_kk.style.setProperty("margin-left","14px");
}

//用户名
/*失去焦点时，js先判断是否为合法，再ajax异步交互，注册名是否可用*/
function username_onblur(){
	clearContent("kk");
	var username=document.getElementById("username");
	username.style.setProperty("border","");
	var regex=/^[a-zA-Z0-9\u4E00-\u9FA5]{5,25}$/;//返回值是boolean类型,判断str是否全为数字和字母和中文(一个符合就行),$表示从头到尾都匹配
	if(regex.test(username.value)){
		$.ajax({
			url:"/admin/checkName",
			type:"POST",
			data:JSON.stringify({username:username.value}),
			contentType:"application/json;charset=utf-8",
			dataType:"text",
			success:function (data) {
				if(data=="false"){
					var username=document.getElementById("username");
	//				username.style.border="1px solid red";//修改样式
					username.style.setProperty("border","1px solid red");//设置样式
					var div_kk=document.createElement("div");
					div_kk.id="kk";
					div_kk.innerHTML="会员名已被注册！";
					document.getElementById("add").appendChild(div_kk);
				}
			}
		})
		
	}else{
		var div_kk=document.createElement("div");
    	div_kk.id="kk";
    	div_kk.innerHTML="会员名为5-25字符，只能包含字母和数字或汉字";
    	document.getElementById("add").appendChild(div_kk);
//		document.getElementsByClassName("add").appendChild(div_kk);//错误!getElementsByClassName没有appendChild方法??
    	username.style.border="1px solid red";
    	div_kk.style.color="";
	}
}

/*得到焦点时，提示密码格式*/
function pwd_onfocus(){
	clearContent("pwd-kk");
	var div_kk=document.createElement("div");
	div_kk.id="pwd-kk";
	div_kk.innerHTML="<ul><li>6-20个字符，密码不能和会员名相同</li><li>只能且必须包含字母和数字</li></ul>";
	document.getElementById("add-pwd").appendChild(div_kk);
	div_kk.style.setProperty("border","2px solid #d4d4d4");
	div_kk.style.setProperty("text-align","left");
	div_kk.style.setProperty("color","#73777a");
	div_kk.style.setProperty("border-radius","16px");
	div_kk.style.setProperty("margin-top","-14px");
	div_kk.style.setProperty("margin-left","14px");
}

/*失去焦点时，检验第一次输入的密码*/
function pwd_onblur(){
	clearContent("pwd-kk");
	var div_kk=document.createElement("div");
	div_kk.id="pwd-kk";
	var pwd=document.getElementById("password");
	pwd.style.setProperty("border","");
	var regex=/^[0-9a-zA-Z]{6,20}$/;
	if(pwd.value.indexOf(" ")>=0){
		div_kk.innerHTML="输入的密码含空格";
		document.getElementById("add-pwd").appendChild(div_kk);
		div_kk.style.setProperty("color","red");
		pwd.style.setProperty("border","1px solid red");
	}else if(!regex.test(pwd.value)){
		div_kk.innerHTML="密码设置不符合要求";
		document.getElementById("add-pwd").appendChild(div_kk);
		div_kk.style.setProperty("color","red");
		pwd.style.setProperty("border","1px solid red");
	}
	//这里可以添加判断密码复杂度，用正则表达式判断是否为纯数字，纯字母，梯度改变变量i++来判断
}

//验证重复输入密码
function RePwd_onblur(){
	clearContent("Rekk");
	var pwd=document.getElementById("password").value;
	var rePwd=document.getElementById("rePassword");
	rePwd.style.setProperty("border","");
	if(pwd!=rePwd.value){
		var div_kk=document.createElement("div");
    	div_kk.id="Rekk";
    	div_kk.innerHTML="两次密码不一致";
    	document.getElementById("addPwd").appendChild(div_kk);
    	rePwd.style.setProperty("border","1px solid red");
	}
}

/*验证号码*/
function phone_onblur(){
	clearContent("phone_kk");
	var phone=document.getElementById("phone").value;
	if(phone==""){
		var div_kk=document.createElement("div");
		div_kk.id="phone_kk";
		div_kk.innerHTML="请输入你的手机号码";
		document.getElementById("addPhone").appendChild(div_kk);
	}else{
//		var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;//这个匹配110都能注册！！！
		var patrn=/^[0-9]{11}$/;
//		alert(phone+" test: "+patrn.test(phone)+" exec:"+patrn.exec(phone));
		if(!patrn.test(phone)){
			var div_kk=document.createElement("div");
			div_kk.id="phone_kk";
			div_kk.innerHTML="手机号码格式不正确，请重新输入";
			document.getElementById("addPhone").appendChild(div_kk);
		}
	}
}


function clearContent(k){
	//添加之前，先把这个div删了
	var div=document.getElementById(k);//div没有value！！！
//	alert(div);    //这里也不能用div.length>=1,官方文档说，没有这个div的话，div=null
	if(div!=null){//这里如果只是div!=undefined也不行，因为这里是异步刷新，第一次创建了div,不管后来有没有被删，只要页面不被刷新，这个div就是定义的了
		div.parentNode.removeChild(div);
	}
}

/*点击登录时，先判断前面几个空是否为空,这样就得先在前面，把不符合的输入框清空数据，但这种方式体验不好*/
//方法二：判断提示的div和输入框是否为空
function register_button(){
	var username=document.getElementById("username");//用户名输入框
	var u=document.getElementById("kk");//用户名提示
	
	var pwd=document.getElementById("password");
	var p=document.getElementById("pwd-kk");
	
	var rePwd=document.getElementById("rePassword");
	var rp=document.getElementById("Rekk");
	
	var phone=document.getElementById("phone");
	var ph=document.getElementById("phone_kk");
	
	if(u==null&&username.value!=""&&p==null&&pwd.value!=""&&rp==null&&rePwd.value!=""&&ph==null&&phone.value!=""){
		//所有输入框都正确，提交给服务器
		$.ajax({
			url:"/admin/register",
			type:"POST",
			data:JSON.stringify({username:username.value,password:pwd.value
				,rePassword:rePwd.value,phone:phone.value,account:"xxxxxxxxx"}),
			contentType:"application/json;charset=UTF-8",
			dataType:"text",
			success:function (data) {
				if(data=="true"){
					layui.use('layer', function () {
						var $ = layui.jquery, layer = layui.layer;
						layer.msg("注册成功，请登录", {
							icon: 16,
							shade: 0.3,
							time: 20000  //时间2000=2s
						});
						var win = window;
						while (win != win.top) {//Top属性返回当前窗口的最顶层浏览器窗口。
							win = win.top;
						}
						win.location.href = "/admin/login.html"
					});
				}else{
					layui.use('layer', function () {
						var $ = layui.jquery, layer = layui.layer;
						layer.open({
							title: "信息",
							content: data
						});
					});
				}
			}
		})
	}else{
		 if (username.value == "") {
			clearContent("kk");
			var div_kk = document.createElement("div");
			div_kk.id = "kk";
			div_kk.innerHTML = "请设置会员名";
			document.getElementById("add").appendChild(div_kk);
			// document.getElementsByClassName("add").appendChild(div_kk);//错误!getElementsByClassName没有appendChild方法??
			username.style.border = "1px solid red";
			div_kk.style.color = "";
		}
		if (pwd.value == "") {
			clearContent("pwd-kk");
			var div_kk = document.createElement("div");
			div_kk.id = "pwd-kk";
			div_kk.innerHTML = "请输入密码";
			document.getElementById("add-pwd").appendChild(div_kk);
			div_kk.style.setProperty("color", "red");
			pwd.style.setProperty("border", "1px solid red");
		}
		if (rePwd.value == "") {
			clearContent("Rekk");
			var div_kk = document.createElement("div");
			div_kk.id = "Rekk";
			div_kk.innerHTML = "两次密码不一致";
			document.getElementById("addPwd").appendChild(div_kk);
			rePwd.style.setProperty("border", "1px solid red");
		}
		if (phone.value == "") {
			clearContent("phone_kk");
			var div_kk = document.createElement("div");
			div_kk.id = "phone_kk";
			div_kk.innerHTML = "请输入你的手机号码";
			document.getElementById("addPhone").appendChild(div_kk);
		}
	}
}