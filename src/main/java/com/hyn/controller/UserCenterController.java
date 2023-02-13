package com.hyn.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyn.anotation.CurrentUser;
import com.hyn.common.ConResult;
import com.hyn.common.dto.req.BaseReqDto;
import com.hyn.dto.req.UserLoginReqDto;
import com.hyn.dto.req.UserQueryReqDto;
import com.hyn.dto.req.UserRegisterReqDto;
import com.hyn.dto.req.UserUpdateReqDto;
import com.hyn.dto.resp.UserRespDto;
import com.hyn.entity.User;
import com.hyn.enums.UserCenterServiceEnum;
import com.hyn.enums.UserRoleEnum;
import com.hyn.exception.BusinessException;
import com.hyn.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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
    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public ConResult<Boolean> userLogout(HttpServletRequest request){
        request.getSession().removeAttribute(UserCenterServiceEnum.USER_LOGIN_STATE.getMsg());
        return ConResult.success(true);
    }
    /**
     * @description 分页获取用户列表
     * @author hyn
     * @date 2022-10-31 09:51
     */
    @PostMapping("/searchUsers")
    @ApiOperation("分页获取用户列表")
    public ConResult<IPage<UserRespDto>> searchUsers(@RequestBody UserQueryReqDto reqDto, HttpServletRequest request) {
        //仅管理员可查询
        User user = (User) request.getSession().getAttribute(UserCenterServiceEnum.USER_LOGIN_STATE.getMsg());
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException(UserCenterServiceEnum.PERMISSION_DENIED);
        }
        if (!user.getUserRole().equals(UserRoleEnum.ADMIN_USER.getCode())){
            throw new BusinessException(UserCenterServiceEnum.PERMISSION_DENIED);
        }
        return ConResult.success(userService.searchUsers(reqDto));
    }

    @GetMapping("/searchUsersByTags")
    @ApiOperation("根据标签去查询用户")
    public ConResult<List<UserRespDto>> searchUsersByTags(@RequestParam(value = "tags",required = false) List<String> tagsList){
        return ConResult.success(userService.searchUsersByTags(tagsList));
    }
    @GetMapping("/getCurrentUser")
    @ApiOperation("获取当前用户的信息")
    public ConResult<UserRespDto> getCurrentUser(HttpServletRequest request){
        return ConResult.success(userService.getCurrentUser(request));
    }
    @PostMapping("/update")
    @ApiOperation("修改用户的信息")
    public ConResult<Boolean> updateUserDetail(@CurrentUser User user, @RequestBody UserUpdateReqDto reqDto){
        return ConResult.success(userService.updateUserDetail(reqDto,user));
    }

    @PostMapping("/recommend")
    @ApiOperation("获取推荐用户")
    public ConResult<IPage<UserRespDto>> recommendUsers(BaseReqDto reqDto){
        return ConResult.success(userService.recommendUsers(reqDto));
    }
}
