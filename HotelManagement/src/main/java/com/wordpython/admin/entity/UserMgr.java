package com.wordpython.admin.entity;

import lombok.Data;

/**
 * @Author wordpython
 * @Date 2019/10/25
 **/

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
@Data
public class UserMgr implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private Long userId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 名字
     */
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别(字典)
     */
    private String sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 角色id(多个逗号隔开)
     */
    private String roleId;

    /**
     * 部门id(多个逗号隔开)
     */
    private Long deptId;

    /**
     * 状态(字典)
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private Long updateUser;

    /**
     * 乐观锁
     */
    private Integer version;
}