/**
 * Copyright 2018-2020 stylefeng & wordpython (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wordpython.admin.controller;
import com.wordpython.admin.entity.AdPage;
import com.wordpython.admin.entity.AdUsers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 系统管理员控制器
 *
 * @author wordpython
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController {

    private static String PREFIX = "admin/user/";

    /**
     * 跳转到查看管理员列表的页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("")
    public String index() {
        System.out.println("跳转到查看管理员列表的页面");
        return PREFIX + "user";
    }

    /**
     * 跳转到添加管理员列表的页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add";
    }

    /**
     * 跳转到角色分配页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
//    @Permission
//    @RequestMapping("/role_assign")
//    public String roleAssign(@RequestParam Long userId, Model model) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        model.addAttribute("userId", userId);
//        return PREFIX + "user_roleassign.html";
//    }

    /**
     * 跳转到编辑管理员页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/user_edit")
    public String userEdit(@RequestParam Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        User user = this.userService.getById(userId);
//        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * 获取用户详情
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
//    @RequestMapping("/getUserInfo")
//    @ResponseBody
//    public Object getUserInfo(@RequestParam Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new RequestEmptyException();
//        }
//
//        this.userService.assertAuth(userId);
//        User user = this.userService.getById(userId);
//        Map<String, Object> map = UserFactory.removeUnSafeFields(user);
//
//        HashMap<Object, Object> hashMap = CollectionUtil.newHashMap();
//        hashMap.putAll(map);
//        hashMap.put("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
//        hashMap.put("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));
//
//        return ResponseData.success(hashMap);
//    }

    /**
     * 修改当前用户的密码
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
//    @RequestMapping("/changePwd")
//    @ResponseBody
//    public Object changePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
//        if (ToolUtil.isOneEmpty(oldPassword, newPassword)) {
//            throw new RequestEmptyException();
//        }
//        this.userService.changePwd(oldPassword, newPassword);
//        return SUCCESS_TIP;
//    }

    /**
     * 查询管理员列表
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long deptId) {

        System.out.println("查询管理员列表");
        //拼接查询条件
        String beginTime = "";
        String endTime = "";
        AdUsers data=new AdUsers("123abc","wordpython","男","超级管理员","开发部","djsk@qq.com","13610079097","2016-01-29 08:49:53", "正常");
        AdPage adPage=new AdPage(Arrays.asList(data),0,12,"啦啦啦");
        return adPage;
    }

    /**
     * 添加管理员
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/add")
//    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
//    @Permission(Const.ADMIN_NAME)
//    @ResponseBody
//    public ResponseData add(@Valid UserDto user, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.userService.addUser(user);
//        return SUCCESS_TIP;
//    }

    /**
     * 修改管理员
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/edit")
//    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
//    @ResponseBody
//    public ResponseData edit(@Valid UserDto user, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.userService.editUser(user);
//        return SUCCESS_TIP;
//    }

    /**
     * 删除管理员（逻辑删除）
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/delete")
//    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
//    @Permission
//    @ResponseBody
//    public ResponseData delete(@RequestParam Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.userService.deleteUser(userId);
//        return SUCCESS_TIP;
//    }

    /**
     * 查看管理员详情
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/view/{userId}")
//    @ResponseBody
//    public User view(@PathVariable Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.userService.assertAuth(userId);
//        return this.userService.getById(userId);
//    }

    /**
     * 重置管理员的密码
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/reset")
//    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
//    @Permission(Const.ADMIN_NAME)
//    @ResponseBody
//    public ResponseData reset(@RequestParam Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.userService.assertAuth(userId);
//        User user = this.userService.getById(userId);
//        user.setSalt(ShiroKit.getRandomSalt(5));
//        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
//        this.userService.updateById(user);
//        return SUCCESS_TIP;
//    }

    /**
     * 冻结用户
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/freeze")
//    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
//    @Permission(Const.ADMIN_NAME)
//    @ResponseBody
//    public ResponseData freeze(@RequestParam Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        //不能冻结超级管理员
//        if (userId.equals(Const.ADMIN_ID)) {
//            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
//        }
//        this.userService.assertAuth(userId);
//        this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
//        return SUCCESS_TIP;
//    }

    /**
     * 解除冻结用户
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/unfreeze")
//    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
//    @Permission(Const.ADMIN_NAME)
//    @ResponseBody
//    public ResponseData unfreeze(@RequestParam Long userId) {
//        if (ToolUtil.isEmpty(userId)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.userService.assertAuth(userId);
//        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
//        return SUCCESS_TIP;
//    }

    /**
     * 分配角色
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping("/setRole")
//    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
//    @Permission(Const.ADMIN_NAME)
//    @ResponseBody
//    public ResponseData setRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
//        if (ToolUtil.isOneEmpty(userId, roleIds)) {
//            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
//        }
//        //不能修改超级管理员
//        if (userId.equals(Const.ADMIN_ID)) {
//            throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN);
//        }
//        this.userService.assertAuth(userId);
//        this.userService.setRoles(userId, roleIds);
//        return SUCCESS_TIP;
//    }

    /**
     * 上传图片
     *
     * @author wordpython
     * @Date 2019/10/14 22:44
     */
//    @RequestMapping(method = RequestMethod.POST, path = "/upload")
//    @ResponseBody
//    public String upload(@RequestPart("file") MultipartFile picture) {
//
//        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
//        try {
//            String fileSavePath = gunsProperties.getFileUploadPath();
//            picture.transferTo(new File(fileSavePath + pictureName));
//        } catch (Exception e) {
//            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
//        }
//        return pictureName;
//    }
}
