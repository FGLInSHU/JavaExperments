package com.fuguoliang.druiddemo.demo.interceptors;

import com.fuguoliang.druiddemo.demo.annotations.LogRequired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author FGL_S
 */
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截处理代码
        HandlerMethod method = (HandlerMethod) handler;
        LogRequired loginRequired = method.getMethodAnnotation(LogRequired.class);
        if (null != loginRequired) {
            System.out.println("[interceptor] preHandle, time is:" + new Date().toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 拦截处理代码
        HandlerMethod method = (HandlerMethod) handler;
        LogRequired loginRequired = method.getMethodAnnotation(LogRequired.class);
        if (null != loginRequired) {
            System.out.println("[interceptor] postHandle, time is:" + new Date().toString());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 拦截处理代码
        HandlerMethod method = (HandlerMethod) handler;
        LogRequired loginRequired = method.getMethodAnnotation(LogRequired.class);
        if (null != loginRequired) {
            System.out.println("[interceptor] afterCompletion, time is:" + new Date().toString());
        }
    }
}
