package com.hyn.enums;


import com.hyn.common.i.IBaseHttpStatusEnum;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 通用错误码
 * @date 2022/2/23
 */
public enum ConHttpStatusEnum implements IBaseHttpStatusEnum {
    /**通用错误码**/
    NO_DATA(-5,"数据不存在，请核对参数无误后再试"),
    ARGUMENTS_FORMAT_ERROR(-4,"请求参数格式错误，请核对参数无误后再操作"),
    DATA_TYPE_ERROR(-3,"数据类型错误"),
    INVALID_ARGUMENTS(-2,"非法参数"),
    SQL_EXEC_FAIL(-1,"SQL执行失败"),
    FIND_MORE_ONE_RECORD(-1,"SQL执行失败，存在多条记录"),
    FAIL(-1,"操作失败"),
    SUCCESS(0, "操作成功"),
    OK(200, "操作成功"),
    BAD_REQUEST(400, "错误请求"),
    UNAUTHORIZED(401, "无访问权限"),
    PAYMENT_REQUIRED(402,"Payment Required"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到"),
    METHOD_NOT_ALLOWED(405,"方法不允许"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    NOT_IMPLEMENTED(501, "尚未实施"),
    BAD_GATEWAY(502, "错误网关"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    ;
    ConHttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public int code() {
        return this.code;
    }
    @Override
    public String message(){
        return this.message;
    }
    private int code;
    private String message;
}
