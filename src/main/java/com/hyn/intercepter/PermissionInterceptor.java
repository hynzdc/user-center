package com.hyn.intercepter;

import com.hyn.entity.User;
import com.hyn.enums.UserCenterServiceEnum;
import com.hyn.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hyn
 * @version 1.0.0
 * @description
 * @date 2023/1/16
 */
@Configuration
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User currentUser = (User) request.getSession().getAttribute(UserCenterServiceEnum.USER_LOGIN_STATE.getMsg());
        if (ObjectUtils.isEmpty(currentUser)){
            throw new BusinessException(UserCenterServiceEnum.USER_NOT_LOGIN);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
