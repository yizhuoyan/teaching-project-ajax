package com.yizhuoyan.teaching.demo.core.config;

import com.yizhuoyan.teaching.demo.controller.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 18/04/17.
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/log/**")
        .excludePathPatterns("/user/login.do")
        .excludePathPatterns("/user/register.do")
        .excludePathPatterns("/user/existAccount.do");
        System.out.println(123);

    }
}
