package com.hyn.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyn
 * @version 1.0.0
 * @description 用户登录入参
 * @date 2022/10/30
 */
@Data
@ApiModel("用户登录入参")
public class UserLoginReqDto {
    @ApiModelProperty("用户帐户")
    private String userAccount;
    @ApiModelProperty("用户密码")
    private String userPassword;
}
