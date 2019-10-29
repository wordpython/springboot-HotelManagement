layui.use(['laydate', 'laypage', 'layer', 'table', 'upload', 'element', 'form', 'ax'], function () {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , upload = layui.upload //上传
        , element = layui.element //元素操作
        , form = layui.form
        , $ax = layui.ax;

    /**
     * 系统管理--用户管理
     */
    var MgrHotel = {
        tableId: "userTable",    //表格id
        condition: {
            name: "",
            deptId: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    MgrHotel.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'hotelId', hide: true, sort: true, title: '房间id'},
            {align: 'center', toolbar: '#tableBarPhoto', width: 90, title: '图片', minWidth: 280},
            {field: 'room_number', sort: true, width: 110, title: '房间编号'},
            {field: 'area', sort: true, title: '面积'},
            {field: 'floor', sort: true, width: 110, title: '所属楼层'},
            {field: 'bed', sort: true, title: '床位数'},
            {field: 'wifi', sort: true, title: 'wifi'},
            {field: 'workbench', sort: true, title: '工作台'},
            {field: 'kitchen', sort: true, title: '厨房'},
            {field: 'win', sort: true, title: '窗口'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'detailed', sort: true, width: 110, title: '特别提示'},
            {field: 'create_time', sort: true, width: 110, title: '创建时间'},
            {field: 'update_time', sort: true, width: 110, title: '更新时间'},
            {field: 'status', sort: true, templet: '#statusTpl', width: 110, title: '房间状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 280}
        ]];
    };

    /**
     * 初始化表格的列
     */
    //执行一个 table 实例
    table.render({
        elem: '#userTable'
        , height: "full-98"
        , cellMinWidth: 100
        , url: './hotel/list/' //数据接口
        , title: '用户表'
        , page: true //开启分页
        // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        , cols: MgrHotel.initColumn()
    });
    // 工具条点击事件
    table.on('tool(' + MgrHotel.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            MgrHotel.onEditUser(data);
        } else if (layEvent === 'delete') {
            MgrHotel.onDeleteUser(data);
        }else if (layEvent === 'show') {
            MgrHotel.showPhoto(data);
        }
    });

    /**
     * 点击查询按钮
     */
    MgrHotel.search = function () {
        var queryData = {};
        queryData['deptId'] = MgrHotel.condition.deptId;
        queryData['name'] = $("#name").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        table.reload(MgrHotel.tableId, {where: queryData});
    };

    /**
     * 弹出添加房间对话框
     */
    MgrHotel.openAddUser = function () {
        top.layui.admin.open({
            type: 2,
            title: '添加新房间',
            area: ["60%", "80%"],//宽，高
            content: '/hotel/hotel_add'
        });
    };

    /**
     * 弹出房间图片框
     */
    MgrHotel.showPhoto = function (data) {
        console.log(data["room_number"]);
        top.layui.admin.open({
            type: 2,
            title: '房间图片',
            area: ["52%", "80%"],//宽，高
            scrollbar: false,
            content: '/hotel/show_photo?room_number=' + data["room_number"], //发送该房间编号
            end: function () {
                // admin.getTempData('formOk') && table.reload(MgrHotel.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + MgrHotel.tableId,
        url: '/hotel/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: MgrHotel.initColumn()
    });
    /**
     * 导出excel按钮
     */
    MgrHotel.exportExcel = function () {
        var checkRows = table.checkStatus(MgrHotel.tableId);
        if (checkRows.data.length === 0) {
            layer.msg("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    MgrHotel.onEditUser = function (data) {
        top.layui.admin.open({
            type: 2,
            title: '编辑用户',
            area: ["52%", "80%"],//宽，高
            content: '/hotel/room_edit?room_number=' + data["room_number"],
            end: function () {
                // admin.getTempData('formOk') && table.reload(MgrHotel.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    MgrHotel.onDeleteUser = function (data) {
        $.ajax({
            url: '/hotel/delete?room_number=' + data["room_number"],
            type: "POST",
            dataType: "text",
            success: function (data) {
                // layer.msg(data);
                //弹出层在提交后关闭，然后刷新父窗口
                layer.close(layer.index);
                window.parent.location.reload();
            }
        });
    };

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MgrHotel.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        MgrHotel.openAddUser();
    });

    // 导出excel
    $('#btnExp').click(function () {
        MgrHotel.exportExcel();
    });
    //修改user状态
    form.on('switch(status)', function (obj) {

        var userId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        MgrHotel.changeUserStatus(userId, checked);
    });
    // 查看图片点击事件
    $('.layui-table-cell.laytable-cell-1-0-2').click(function () {
        MgrHotel.openPhoto();
    });

});