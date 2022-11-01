/**
 * @mbg.generated generator on Sat Oct 29 20:52:10 CST 2022
 */
package com.hyn.service.impl;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyn.dto.req.UserQueryReqDto;
import com.hyn.dto.resp.UserRespDto;
import com.hyn.entity.User;
import com.hyn.enums.UserCenterServiceEnum;
import com.hyn.exception.BusinessException;
import com.hyn.mapper.UserMapper;
import com.hyn.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final String SALT = "hynzdc";

    /**
     * @description 用户登录态键
     * @author hyn
     * @date 2022-10-30 20:50
     */
    private static final String USER_LOGIN_STATE = "userLoginState";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(UserCenterServiceEnum.USER_REGISTER_DETAIL_NO_BLANK);
        }
        //2.校验帐户
        if (userAccount.length() < 4) {
            throw new BusinessException(UserCenterServiceEnum.USER_ACCOUNT_LESS_THEN_FOUR);
        }
        //3.校验用户密码的位数
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(UserCenterServiceEnum.USER_PASSWORD_LESS_THEN_EIGHT);
        }
        //帐户不能包含特殊字符
        String validPatten = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPatten).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(UserCenterServiceEnum.USER_ACCOUNT_HAVE_SPECIAL_CHARACTERS);
        }
        //密码和校验密码是否相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(UserCenterServiceEnum.USER_TWO_DIFFERENT_PASSWORDS);
        }
        //4帐户不可以重复
        Integer count = this.lambdaQuery().eq(User::getUserAccount, userAccount).count();
        if (count > 0) {
            throw new BusinessException(UserCenterServiceEnum.USER_ACCOUNT_HAVE_USE);
        }
        //加密
        String encryptionPwd = SecureUtil.md5(SALT + userPassword);
        //在数据库插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptionPwd);
        boolean save = this.save(user);
        if (!save) {
            throw new BusinessException(UserCenterServiceEnum.USER_FAIL_REGISTER);
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(UserCenterServiceEnum.USER_LOGIN_DETAIL_NO_BLANK);
        }
        //2.校验帐户
        if (userAccount.length() < 4) {
            throw new BusinessException(UserCenterServiceEnum.USER_ACCOUNT_LESS_THEN_FOUR);
        }
        //3.校验用户密码的位数
        if (userPassword.length() < 8) {
            throw new BusinessException(UserCenterServiceEnum.USER_PASSWORD_LESS_THEN_EIGHT);
        }
        //帐户不能包含特殊字符
        String validPatten = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPatten).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(UserCenterServiceEnum.USER_ACCOUNT_HAVE_SPECIAL_CHARACTERS);
        }
        //对密码进行加密
        String encryptionPassword = SecureUtil.md5(SALT + userPassword);
        //查询用户是否存在根据账号和密码,返回user对象
        User loginUser = this.lambdaQuery().eq(User::getUserAccount, userAccount).eq(User::getUserPassword, encryptionPassword).one();
        //帐户不存在
        if (ObjectUtils.isEmpty(loginUser)) {
            throw new BusinessException(UserCenterServiceEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //用户脱敏
        User safetyUser = getSafetyUser(loginUser);
        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        //返回这个用户
        return safetyUser;
    }

    @Override
    public IPage<UserRespDto> searchUsers(UserQueryReqDto reqDto) {
        Page<User> page = new Page<>(reqDto.getPageNo(), reqDto.getIsPage() == 1 ? reqDto.getPageSize() : reqDto.getIsPage());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .like(StringUtils.isNotEmpty(reqDto.getUsername()),"username",reqDto.getUsername())
                .eq(StringUtils.isNotEmpty(reqDto.getPhone()), "phone", reqDto.getPhone());
        return this.baseMapper.searchUsers(page,userQueryWrapper);
    }

    /**
     * @param loginUser
     * @return com.hyn.entity.User
     * @description 用户脱敏
     * @author hyn
     * @date 2022-10-30 21:00
     */
    private static User getSafetyUser(User loginUser) {
        User safetyUser = new User();
        safetyUser.setId(loginUser.getId());
        safetyUser.setUsername(loginUser.getUsername());
        safetyUser.setUserAccount(loginUser.getUserAccount());
        safetyUser.setAvatarUrl(loginUser.getAvatarUrl());
        safetyUser.setGender(loginUser.getGender());
        safetyUser.setUserPassword(DesensitizedUtil.password(loginUser.getUserPassword()));
        safetyUser.setPhone(DesensitizedUtil.mobilePhone(loginUser.getPhone()));
        safetyUser.setEmail(loginUser.getEmail());
        safetyUser.setUserStatus(loginUser.getUserStatus());
        safetyUser.setCreateTime(loginUser.getCreateTime());
        safetyUser.setUpdateTime(loginUser.getUpdateTime());
        safetyUser.setUserRole(loginUser.getUserRole());
        safetyUser.setPlanetCode(loginUser.getPlanetCode());
        return safetyUser;
    }


}