package com.hyn.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2023/1/13
 */
@Data
@ApiModel("用户修改入参对象")
public class UserUpdateReqDto implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户昵称")
    private String username;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("性别")
    private Byte gender;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("用户标签")
    private String tags;
    @ApiModelProperty("邮箱")
    private String email;
}
