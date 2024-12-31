package com.icia.board.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class SessionInterceptor implements AsyncHandlerInterceptor {
    //요청1: /board/list  --> 인터셉트--> 요청2:로그인 성공---> /board/list
    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //localhost/board/list?p=1&size=10
        log.info("=====preHandle call uri={}", request.getRequestURI());
        log.info("=====preHandle call queryString={}", request.getQueryString());
        HttpSession session = request.getSession();
        if(session.getAttribute("member")==null){
            log.info("======인터셉트!--로그인 안함.");
            session.setAttribute("urlPrior_login", request.getRequestURI()+'?'+request.getQueryString());
            response.sendRedirect("/member/login");  //redirect는 get만 요청가능
            return false;  // 컨트롤러 진입 금지
        }
        return true;  //컨트롤러 진입
    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("view 직전에 호출");
//    }
}
