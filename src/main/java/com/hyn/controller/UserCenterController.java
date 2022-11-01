package com.hyn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyn.common.ConResult;
import com.hyn.dto.req.UserLoginReqDto;
import com.hyn.dto.req.UserQueryReqDto;
import com.hyn.dto.req.UserRegisterReqDto;
import com.hyn.dto.resp.UserRespDto;
import com.hyn.entity.User;
import com.hyn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hyn
 * @version 1.0.0
 * @description 用户中心controller
 * @date 2022/10/30
 */
@RestController
@Api(tags = "用户中心")
public class UserCenterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ConResult<Long> userRegister(@RequestBody UserRegisterReqDto reqDto){
        return ConResult.success(userService.userRegister(reqDto.getUserAccount(),reqDto.getUserPassword() , reqDto.getCheckPassword()));
    }
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ConResult<User> userLogin(@RequestBody UserLoginReqDto reqDto, HttpServletRequest request){
        return ConResult.success(userService.userLogin(reqDto.getUserAccount(),reqDto.getUserPassword(),request));
    }
    /**
     * @description 分页获取用户列表
     * @author hyn
     * @date 2022-10-31 09:51
     */
    @PostMapping("/searchUsers")
    @ApiOperation("分页获取用户列表")
    public ConResult<IPage<UserRespDto>> searchUsers(@RequestBody UserQueryReqDto reqDto){
        return ConResult.success(userService.searchUsers(reqDto));
    }

}
