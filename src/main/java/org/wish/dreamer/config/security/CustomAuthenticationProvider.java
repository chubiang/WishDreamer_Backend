package org.wish.dreamer.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("USERNAME = {}, PASSWORD = {}",name, password);

        // TODO DB접근
        if (name != null && name.equals("nana"))
        {
            log.info("USERNAME = {}, PASSWORD = {}",name, password);
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
