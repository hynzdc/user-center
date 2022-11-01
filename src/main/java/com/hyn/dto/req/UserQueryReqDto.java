package com.hyn.dto.req;

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
@ApiModel("用户查询入参")
public class UserQueryReqDto extends BaseReqDto {
    @ApiModelProperty("用户姓名")
    private String username;
    @ApiModelProperty("手机号")
    private String phone;
}
