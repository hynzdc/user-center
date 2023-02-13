package com.hyn.dto.resp;

import com.hyn.common.dto.req.BaseReqDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2022/11/1
 */
@Data
@ApiModel("用户出参对象")
public class UserRespDto{
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户姓名")
    private String username;
    @ApiModelProperty("帐户")
    private String userAccount;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("性别")
    private Byte gender;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("用户标签")
    private String tags;
    @ApiModelProperty("星球编号")
    private String planetCode;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("用户状态")
    private Integer userStatus;
    @ApiModelProperty("用户角色")
    private Integer userRole;
    @ApiModelProperty("用户创建时间")
    private String createTime;
}
