package com.hyn.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hyn
 * @version 1.0.0
 * @description 用户注册入参
 * @date 2022/10/30
 */
@Data
@ApiModel("用户注册入参对象")
public class UserRegisterReqDto implements Serializable {

    /**用户帐户**/
    @ApiModelProperty("用户帐户")
    private String userAccount;
    /**用户密码**/
    @ApiModelProperty("用户密码")
    private String userPassword;
    /**检查密码**/
    @ApiModelProperty("检查密码")
    private String checkPassword;
}
