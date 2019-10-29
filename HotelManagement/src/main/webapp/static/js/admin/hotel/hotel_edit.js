/**
 * 用户详情对话框
 */
var UserInfoDlg = {
    data: {
        deptId: "",
        deptName: ""
    }
};

layui.use(['layer', 'form', 'laydate'], function () {
    var $ = layui.jquery;
    var form = layui.form;
    var laydate = layui.laydate;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    // admin.iframeAuto();

    //获取房间信息
    // var ajax = new $ax("/hotel/getRoomInfo");
    // var result = ajax.start();
    // form.val('roomForm', result.data);
    $(function(){
        //获取总页数
        $.ajax({
            url:"/hotel/getRoomInfo",
            type:"POST",
            dataType:"json",
            success:function(data){
                console.log(data);
                form.val('roomForm', data);
            }
        });
    });

    //监听提交
    form.on('submit(formDemo)', function (data) {
        console.log(JSON.stringify(data.field));//将对象变成json字符串
        console.log(data.field);//将对象变成json字符串
        $.ajax({
            url: "/hotel/edit",
            type: "POST",
            data: JSON.stringify(data.field),
            dataType: "text",
            contentType: "application/json;charset-UTF-8",
            success: function (data) {
                //弹出层在提交后关闭，然后刷新父窗口
                layer.close(layer.index);
                window.parent.location.reload();
            }
        });
    });
});