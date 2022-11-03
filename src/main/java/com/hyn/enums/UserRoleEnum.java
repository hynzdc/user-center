package com.hyn.enums;

/**
 * @author hyn
 * @version 1.0.0
 * @description 用户角色枚举
 * @date 2022/11/3
 */
public enum UserRoleEnum {
    NORMAL_USER(0,"普通用户"),
    ADMIN_USER(1,"管理员"),
    ;
    private Integer code;
    private String role;

    public Integer getCode() {
        return code;
    }

    public String getRole() {
        return role;
    }

    UserRoleEnum(Integer code, String role) {
        this.code = code;
        this.role = role;
    }
}
