package com.dpb.nsepback.config.interceptor;

import com.dpb.nsepback.common.Constants;
import com.dpb.nsepback.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BugControllerGuardInterceptor implements HandlerInterceptor {

    @Value("${nsep.bug-controller.enabled:false}")
    private boolean bugControllerEnabled;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String packageName = handlerMethod.getBeanType().getPackage().getName();
        if (!bugControllerEnabled && (packageName.equals("com.dpb.nsepback.controller.BUGcontroller")
                || packageName.startsWith("com.dpb.nsepback.controller.BUGcontroller."))) {
            throw new ServiceException(Constants.CODE_401, "高危教学接口已禁用");
        }
        return true;
    }
}
