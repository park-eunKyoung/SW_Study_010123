package com.icia.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//레거시 프로젝트 환경설정 spring.xml
@Configuration //boot 환경설정 java config
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    SessionInterceptor sessionInterceptor; //preHandler 세워놓음

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
        //controller 에서
        .addPathPatterns("/**")//member/join/1~ 몇단계이던 상관없을땐 별두개(**)
            .excludePathPatterns("/","/member/login","/member/logout")
                .excludePathPatterns("/member/join")
                    .excludePathPatterns("/js/**","/css/**","/img/**"); //get,post 둘다 잡음
    }
}
