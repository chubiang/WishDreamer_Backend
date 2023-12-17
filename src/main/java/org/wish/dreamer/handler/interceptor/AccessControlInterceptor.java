package org.wish.dreamer.handler.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class AccessControlInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("AccessControlInterceptor {}", request.getHeader("Access-Control-Allow-Origin"));
        if (!request.getHeader("Access-Control-Allow-Origin").isEmpty()) {
            response.setHeader("Accept", "application/json, text/plain, */*");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:5137/");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        }
    }
}
