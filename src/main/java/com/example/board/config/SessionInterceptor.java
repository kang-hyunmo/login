package com.example.board.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class SessionInterceptor implements AsyncHandlerInterceptor {
    // /board/list
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //localhost/board/list?p=1&size=10
        log.info("preHandle call url={}", request.getRequestURI());
        log.info("preHandle call queryString={}", request.getQueryString());

        HttpSession session = request.getSession();
        if(session.getAttribute("member") == null) {
            log.info("인터셉트 - 로그인 안함");
            session.setAttribute("url Prior",request.getRequestURI()+'?'+request.getQueryString());
//            session.setAttribute("url Prior", request.getRequestURI());
            response.sendRedirect("member/login");
            return false;
        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
}
