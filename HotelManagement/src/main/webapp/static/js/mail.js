/**
 * 邮箱登陆
 */

/*获取验证码按钮*/
function send_code() {
    var mail = document.getElementById("mail").value;
    //判断邮件格式
    var regex = /^[a-z0-9](\w|\.|-)*@([a-z0-9]+-?[a-z0-9]+\.){1,3}[a-z]{2,4}$/i;
    if (regex.test(mail)) {
        //加载中的应用场景
        var index;
        layui.use('layer', function () {
            var $ = layui.jquery, layer = layui.layer;
            index = layer.msg('验证码发送中，请耐心等待', {
                icon: 16,
                shade: 0.3,
                time: false  //时间改成false就可以了，他就会一直的转动,ajax进行请求成功后，再关闭关闭这个提示
            });
        });
        $.ajax({
            url: "sendMail",
            type: "POST",
            data: JSON.stringify({mail: mail}),
            contentType: "application/json;charset=UTF-8",
            dataType: "text",
            success: function (data) {
                layer.close(index);
                layer.open({
                    title: "信息",
                    content: data
                });
            }
        });
    } else {
        layui.use('layer', function () {
            var $ = layui.jquery, layer = layui.layer;
            layer.open({
                title: "信息",
                content: "请输入有效邮箱"
            });
        });
    }
}

/*登录按钮*/
function login_button() {
    var mail = document.getElementById("mail");
    var pwd = document.getElementById("password");
    var regex = /^[a-z0-9](\w|\.|-)*@([a-z0-9]+-?[a-z0-9]+\.){1,3}[a-z]{2,4}$/i;
    var reg = /^[0-9]{6}$/;
    if (reg.test(pwd.value) && regex.test(mail.value)) {
        $.ajax({
            url: "mailLogin",
            type: "POST",
            data: JSON.stringify({mail: mail.value, code: pwd.value}),
            contentType: "application/json;charset=UTF-8",
            dataType: "text",
            success: function (data) {
                if (data == "search") {
                    var win = window;
                    while (win != win.top) {//Top属性返回当前窗口的最顶层浏览器窗口。
                        win = win.top;
                    }
                    win.location.href = "/search"
                } else {
                    layui.use('layer', function () {
                        var $ = layui.jquery, layer = layui.layer;
                        layer.open({
                            title: "信息",
                            content: data
                        });
                    });
                }
            }
        });
    } else {
        layui.use('layer', function () {
            var $ = layui.jquery, layer = layui.layer;
            layer.open({
                title: "信息",
                content: "请输入有效邮箱或验证码"
            });
        });
    }
}

