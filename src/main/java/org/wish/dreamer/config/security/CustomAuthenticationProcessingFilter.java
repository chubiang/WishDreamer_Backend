package org.wish.dreamer.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

@Slf4j
public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {


    protected CustomAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String id = null;
        String password = null;
        Enumeration parameterNames  = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement().toString();
           if (paramName.equals("id")) {
                id = request.getParameter(paramName);
            } else if (paramName.equals("pw")) {
                password = request.getParameter(paramName);
            }
        }
        log.info("id = {} , password = {}", id, password);
        return getAuthenticationManager().authenticate(new CustomAuthenticationToken(id, password));
    }

}
