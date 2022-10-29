package com.hyn.common.i;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 基础响应状态枚举类
 * @date 2022/6/29
 */
public interface IBaseHttpStatusEnum {
    default String message(){
        return "SUCCESS";
    }
    default int code() {
        return 0;
    }
}
