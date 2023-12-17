package org.wish.dreamer.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess {}", request.getRequestURL().toString());
        if (!request.getHeader("Access-Control-Allow-Origin").isEmpty()) {
            response.setHeader("Accept", "application/json, text/plain, */*");
            response.setHeader("Content-Type", "application/x-www-form-urlencoded");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        }
    }
}
