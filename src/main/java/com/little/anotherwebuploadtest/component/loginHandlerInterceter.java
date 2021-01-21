package com.little.anotherwebuploadtest.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luo x
 * @date 2020-12-13 09:24
 */


public class loginHandlerInterceter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cp=request.getRequestURI();
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null&&cp.indexOf("user")>0){
            //未登陆，返回登陆界面
            request.setAttribute("message","请先登陆！！");
            request.getRequestDispatcher("/needLogin").forward(request,response);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
