package com.hyn.resolver;

import com.hyn.anotation.CurrentUser;
import com.hyn.entity.User;
import com.hyn.enums.UserCenterServiceEnum;
import com.hyn.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2023/1/13
 */
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        User currentUser = (User)request.getSession().getAttribute(UserCenterServiceEnum.USER_LOGIN_STATE.getMsg());
        if (ObjectUtils.isEmpty(currentUser)){
            throw new BusinessException(UserCenterServiceEnum.USER_NOT_FOUND);
        }
        currentUser.setUserPassword("");
        return currentUser;
    }
}
