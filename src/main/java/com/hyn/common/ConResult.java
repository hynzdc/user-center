package com.hyn.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hyn.enums.ConHttpStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 通用响应信息主体
 * @date 2022/01/17
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @Getter
    @Setter
    private int code;

    /**
     * 返回内容
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 数据对象
     */
    @Getter
    @Setter
    private T data;

    public static <T> ConResult<T> success() {
        return restResult(null, ConHttpStatusEnum.SUCCESS.code(), ConHttpStatusEnum.SUCCESS.message());
    }

    public static <T> ConResult<T> success(T data) {
        return restResult(data, ConHttpStatusEnum.SUCCESS.code(), ConHttpStatusEnum.SUCCESS.message());
    }

    public static <T> ConResult<T> success(T data, String msg) {
        return restResult(data, ConHttpStatusEnum.SUCCESS.code(), msg);
    }

    public static <T> ConResult<T> fail() {
        return restResult(null, ConHttpStatusEnum.FAIL.code(), ConHttpStatusEnum.FAIL.message());
    }

    public static <T> ConResult<T> fail(String msg) {
        return restResult(null, ConHttpStatusEnum.FAIL.code(), msg);
    }
    public static <T> ConResult<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> ConResult<T> fail(T data) {
        return restResult(data, ConHttpStatusEnum.FAIL.code(), ConHttpStatusEnum.FAIL.message());
    }

    private static <T> ConResult<T> restResult(T data, int code, String msg) {
        ConResult<T> apiResult = new ConResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
