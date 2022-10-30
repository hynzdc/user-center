package com.hyn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表名：user
 * 表注释：用户
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private Long id;

    /**
     * 用户昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 账号
     */
    @TableField(value = "userAccount")
    private String userAccount;

    /**
     * 用户头像
     */
    @TableField(value = "avatarUrl")
    private String avatarUrl;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Byte gender;

    /**
     * 密码
     */
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 状态 0 - 正常
     */
    @TableField(value = "userStatus")
    private Integer userStatus;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "isDelete")
    private Byte isDelete;

    /**
     * 用户角色 0 - 普通用户 1 - 管理员
     */
    @TableField(value = "userRole")
    private Integer userRole;

    /**
     * 星球编号
     */
    @TableField(value = "planetCode")
    private String planetCode;
}