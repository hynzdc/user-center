package com.hyn.exception;

import com.hyn.enums.UserCenterServiceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyn
 * @version 1.0.0
 * @description 统一异常类
 * @date 2022/10/29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessException extends RuntimeException{
    private String msg;
    private Integer code;
    public BusinessException(UserCenterServiceEnum userCenterServiceEnum){
        this.code = userCenterServiceEnum.getCode();
        this.msg = userCenterServiceEnum.getMsg();
    }
    public BusinessException(String msg){}
}
