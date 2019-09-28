/**
 * 滑块
 */

window.onload = function() {
	var nick1 = document.getElementById("nick1");
	var dragBg = document.getElementById("dragBg");
	var dragText = document.getElementById("dragText");
	var dragHandler = document.getElementById("dragHandler");

	//滑块最大偏移量
	var maxHandlerOffset = nick1.clientWidth - dragHandler.clientWidth;
	//是否验证成功的标记
	var isVertifySucc = false;
	initDrag();

	function initDrag() {
		dragText.textContent = "请按住滑块，拖动到最右边";
		dragHandler.addEventListener("mousedown", onDragHandlerMouseDown);

		dragHandler.addEventListener("touchstart", onDragHandlerMouseDown);
	}

	function onDragHandlerMouseDown(event) {
		document.addEventListener("mousemove", onDragHandlerMouseMove);
		document.addEventListener("mouseup", onDragHandlerMouseUp);

		document.addEventListener("touchmove", onDragHandlerMouseMove);
		document.addEventListener("touchend", onDragHandlerMouseUp);
	}

	function onDragHandlerMouseMove(event) {
		/*
		html元素不存在width属性,只有clientWidth
		offsetX是相对当前元素的,clientX和pageX是相对其父元素的
		touches：表示当前跟踪的触摸操作的touch对象的数组。
		targetTouches：特定于事件目标的Touch对象的数组。
		changedTouches：表示自上次触摸以来发生了什么改变的Touch对象的数组。
		 */
		var left = (event.clientX || event.changedTouches[0].clientX)
				- dragHandler.clientWidth / 2-515.5;
		if (left < 0) {
			left = 0;
		} else if (left > maxHandlerOffset) {
			left = maxHandlerOffset;
			verifySucc();
		}
		dragHandler.style.left = left + "px";
		dragBg.style.width = dragHandler.style.left;
	}
	function onDragHandlerMouseUp(event) {
		document.removeEventListener("mousemove", onDragHandlerMouseMove);
		document.removeEventListener("mouseup", onDragHandlerMouseUp);

		document.removeEventListener("touchmove", onDragHandlerMouseMove);
		document.removeEventListener("touchend", onDragHandlerMouseUp);

		dragHandler.style.left = 0;
		dragBg.style.width = 0;
	}

	//验证成功
	function verifySucc() {
		isVertifySucc = true;
		dragText.textContent = "验证通过";
		dragText.style.color = "white";
		dragHandler.setAttribute("class", "dragHandlerOkBg");

		dragHandler.removeEventListener("mousedown", onDragHandlerMouseDown);
		document.removeEventListener("mousemove", onDragHandlerMouseMove);
		document.removeEventListener("mouseup", onDragHandlerMouseUp);

		dragHandler.removeEventListener("touchstart", onDragHandlerMouseDown);
		document.removeEventListener("touchmove", onDragHandlerMouseMove);
		document.removeEventListener("touchend", onDragHandlerMouseUp);
		
		
		//发送短信
		send_button();
	}
}

function send_button() {
	var index;
	layui.use('layer', function () {
		var $ = layui.jquery, layer = layui.layer;
		index=layer.msg("手机验证码已发送，请耐心等待", {
			icon: 16,
			shade: 0.3,
			time: false  //时间改成false就可以了，他就会一直的转动,ajax进行请求成功后，再关闭关闭这个提示
		});
	});
	/*发送手机号，获取验证码*/
	$.ajax({
		url: "sendPhone",
		type: "POST",
		data: JSON.stringify({phone: $('.code input-register-code input-error').value}),
		contentType: "application/json;charset=UTF-8",
		dataType: "text",
		success: function (data) {
			layer.close(index);
			if(data=="true"){
				$('#dl').dialog('open');
				//修改样式，设置遮罩层
				divset=$('body');
				for (var i = 0; i<divset.length;i++) {
					divset[i].style.opacity=1;
				};
				for (var i = 0; i<$('.register_body').length;i++) {
					$('.register_body')[i].style.opacity=0.5;
				};
			}
		}
	});

}
function closeDialog() {
	/*发送手机号，获取验证码*/
	$.ajax({
		url: "sendPhone",
		type: "POST",
		data: JSON.stringify({phone: $('.code input-register-code input-error').value}),
		contentType: "application/json;charset=UTF-8",
		dataType: "text",
		success: function (data) {
			layer.close(index);
			if(data=="true"){
				$('#dl').dialog('open');
				//修改样式，设置遮罩层
				divset=$('body');
				for (var i = 0; i<divset.length;i++) {
					divset[i].style.opacity=1;
				};
				for (var i = 0; i<$('.register_body').length;i++) {
					$('.register_body')[i].style.opacity=0.5;
				};
			}
		}
	});

	layui.use('layer', function () {
		var $ = layui.jquery, layer = layui.layer;
		layer.open({
			title: "信息",
			content: "已经重新发送验证码，请留意查收"
		});
	});
	//清空输入框的内容
	$('.code.input-register-code.input-error').val("");
}
function okDialog(){
	//验证码正确则关闭会话窗口，错误则提示并清空
	$('#dl').dialog('close');
	divset=$('body');
	for (var i = 0; i<divset.length;i++) {
		divset[i].style.opacity=0.72;
	};
	for (var i = 0; i<$('.register_body').length;i++) {
		$('.register_body')[i].style.opacity=1;
	};
}
