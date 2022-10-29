package com.hyn.enums;

/**
 * @author hyn
 * @version 1.0.0
 * @description 用户中心服务枚举
 * @date 2022/10/29
 */
public enum UserCenterServiceEnum {
    USER_REGISTER_DETAIL_NO_BLANK(001,"用户注册信息不可为空"),
    USER_ACCOUNT_LESS_THEN_FOUR(002,"用户帐户小于4位"),
    USER_PASSWORD_LESS_THEN_EIGHT(003,"用户密码小于8位"),
    USER_ACCOUNT_HAVE_USE(004,"该账户已被使用"),
    USER_ACCOUNT_HAVE_SPECIAL_CHARACTERS(005,"用户名含有特殊字符"),
    USER_TWO_DIFFERENT_PASSWORDS(006,"两次输入的密码不同"),
    ;

    UserCenterServiceEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String msg;
}
