/**
 * @mbg.generated generator on Sat Oct 29 20:52:10 CST 2022
 */
package com.hyn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyn.dto.req.UserQueryReqDto;
import com.hyn.dto.resp.UserRespDto;
import com.hyn.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    /**
     * @description
     * @author hyn
     * @param userAccount 用户帐户
     * @param userPassword 用户密码
     * @param checkPassword 检查密码
     * @date 2022-10-29 21:02  时间
     * @return long 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
    User userLogin(String userAccount,String userPassword,HttpServletRequest request);

    IPage<UserRespDto> searchUsers(UserQueryReqDto reqDto);
}