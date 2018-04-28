package com.yizhuoyan.teaching.demo.controller;

import com.yizhuoyan.core.ThisSystemUtil;
import com.yizhuoyan.teaching.demo.core.EncrypDES;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 18/04/17.
 */

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        System.out.println(uri);
        String token=request.getParameter("token");
        if(token==null){
            token=request.getHeader("token");
        }
        if(StringUtils.hasText(token)){
            try {
                String account = EncrypDES.decryptor(token);
                request.setAttribute("account",account);
                return true;
            }catch (Exception e){
                request.setAttribute("reason","非法token");
            }
        }else{
            request.setAttribute("reason","请在请求头中传入token");
        }
        request.getRequestDispatcher("/noAuthorizaiton.do").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
