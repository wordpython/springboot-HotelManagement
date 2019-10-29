
layui.use(['laydate', 'laypage', 'layer', 'table', 'upload', 'element','form','ztree', 'ax'], function(){
    var laydate = layui.laydate //日期
        ,laypage = layui.laypage //分页
        ,layer = layui.layer //弹层
        ,table = layui.table //表格
        ,upload = layui.upload //上传
        ,element = layui.element //元素操作
        ,form = layui.form
        , $ZTree = layui.ztree
        ,$ax = layui.ax;

    /**
     * 系统管理--用户管理
     */
    var MgrUser = {
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
    //执行一个 table 实例
    table.render({
        elem: '#userTable'
        ,height: "full-98"
        ,cellMinWidth: 100
        ,url: '/mgr/list/' //数据接口
        ,title: '用户表'
        ,page: true //开启分页
        // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,cols: [[
            {type: 'checkbox'},
            {field: 'userId', hide: true, sort: true, title: '用户id'},
            {field: 'account', sort: true, title: '账号'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'sexName', sort: true, title: '性别'},
            {field: 'roleName', sort: true, title: '角色'},
            {field: 'deptName', sort: true, title: '部门'},
            {field: 'email', sort: true, title: '邮箱'},
            {field: 'phone', sort: true, title: '电话'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'status', sort: true, templet: '#statusTpl', title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 280}
        ]]
    });
    // 工具条点击事件
    table.on('tool(' + MgrUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            layer.msg("修改");
            MgrUser.onEditUser(data);
        } else if (layEvent === 'delete') {
            layer.msg("删除");
            MgrUser.onDeleteUser(data);
        } else if (layEvent === 'roleAssign') {
            layer.msg("分配角色");
            MgrUser.roleAssign(data);
        } else if (layEvent === 'reset') {
            layer.msg("重置密码");
            MgrUser.resetPassword(data);
        }
    });


    /**
     * 选择部门时
     */
    MgrUser.onClickDept = function (e, treeId, treeNode) {
        MgrUser.condition.deptId = treeNode.id;
        MgrUser.search();
    };

    /**
     * 点击查询按钮
     */
    MgrUser.search = function () {
        var queryData = {};
        queryData['deptId'] = MgrUser.condition.deptId;
        queryData['name'] = $("#name").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        table.reload(MgrUser.tableId, {where: queryData});
    };

    /**
     * 弹出添加用户对话框
     */
    MgrUser.openAddUser = function () {
        top.layui.admin.open({
            type: 2,
            title: '添加用户',
            area:['360px','590px'],
            content: '/mgr/user_add',
            end: function () {
                // admin.getTempData('formOk') && table.reload(MgrUser.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    MgrUser.exportExcel = function () {
        var checkRows = table.checkStatus(MgrUser.tableId);
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
    MgrUser.onEditUser = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑用户',
            content: Feng.ctxPath + '/mgr/user_edit?userId=' + data.userId,
            end: function () {
                admin.getTempData('formOk') && table.reload(MgrUser.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    MgrUser.onDeleteUser = function (data) {
        var operation = function () {
            var ajax = new $ax("/mgr/delete", function () {
                table.reload(MgrUser.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userId", data.userId);
            ajax.start();
        };
        Feng.confirm("是否删除用户" + data.account + "?", operation);
    };

    /**
     * 分配角色
     *
     * @param data 点击按钮时候的行数据
     */
    MgrUser.roleAssign = function (data) {
        layer.open({
            type: 2,
            title: '角色分配',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/mgr/role_assign?userId=' + data.userId,
            end: function () {
                table.reload(MgrUser.tableId);
            }
        });
    };

    /**
     * 重置密码
     *
     * @param data 点击按钮时候的行数据
     */
    MgrUser.resetPassword = function (data) {
        Feng.confirm("是否重置密码为111111 ?", function () {
            var ajax = new $ax("/mgr/reset", function (data) {
                Feng.success("重置密码成功!");
            }, function (data) {
                Feng.error("重置密码失败!");
            });
            ajax.set("userId", data.userId);
            ajax.start();
        });
    };

    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param checked 是否选中（true,false），选中就是解锁用户，未选中就是锁定用户
     */
    MgrUser.changeUserStatus = function (userId, checked) {
        if (checked) {
            var ajax = new $ax(Feng.ctxPath + "/mgr/unfreeze", function (data) {
                Feng.success("解除冻结成功!");
            }, function (data) {
                Feng.error("解除冻结失败!");
                table.reload(MgrUser.tableId);
            });
            ajax.set("userId", userId);
            ajax.start();
        } else {
            var ajax = new $ax(Feng.ctxPath + "/mgr/freeze", function (data) {
                Feng.success("冻结成功!");
            }, function (data) {
                Feng.error("冻结失败!" + data.responseJSON.message + "!");
                table.reload(MgrUser.tableId);
            });
            ajax.set("userId", userId);
            ajax.start();
        }
    };

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
    });

    //初始化左侧部门树
    // var ztree = new $ZTree("deptTree", "/dept/tree");
    // ztree.bindOnClick(MgrUser.onClickDept);
    // ztree.init();

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        MgrUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        layer.alert("添加按钮点击事件");
        MgrUser.openAddUser();
    });

    // 导出excel
    $('#btnExp').click(function () {
        layer.alert("导出excel");
        MgrUser.exportExcel();
    });
    //修改user状态
    form.on('switch(status)', function (obj) {

        var userId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        MgrUser.changeUserStatus(userId, checked);
    });

});