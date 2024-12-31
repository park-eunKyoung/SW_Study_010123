package com.icia.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//레거시 프로젝트 환경설정  root-context.xml   spring.xml
@Configuration  //boot 환경설정 java config
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    SessionInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
        //모든 경로의 url 인터셉터
        .addPathPatterns("/**")     //board/list/1
        // 인터셉터 제외할 url
        .excludePathPatterns("/", "/member/login", "/member/logout")
        .excludePathPatterns("/member/join")
        .excludePathPatterns("/js/**", "/css/**", "/img/**")
        .excludePathPatterns("/favicon.ico","/error");
    }
}
