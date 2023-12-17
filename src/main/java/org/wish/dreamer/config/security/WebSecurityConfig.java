package org.wish.dreamer.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.wish.dreamer.handler.LoginFailureHandler;
import org.wish.dreamer.handler.LoginSuccessHandler;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager custAuthenticationManager() {
        return authentication -> {
            if (authentication.isAuthenticated()) {
                CustomAuthenticationToken user = new CustomAuthenticationToken(authentication.getName(), authentication.getCredentials().toString());
                log.info("CustomAuthenticationToken = {}", authentication);
                return new UsernamePasswordAuthenticationToken(user.getPrincipal(), user.getCredentials());
            }
            log.info("CustomAuthenticationToken = {}", authentication);
            throw new UsernameNotFoundException(authentication.getName());
        };
    }

    @Bean
    public CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter() throws Exception{
        CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter(
                new AntPathRequestMatcher("/api/procLogin", HttpMethod.POST.name()));
        filter.setAuthenticationManager(custAuthenticationManager());
        filter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        filter.setAuthenticationFailureHandler(new LoginFailureHandler());

        return filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .addFilterBefore(customAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .authenticationProvider(new CustomAuthenticationProvider())
            .authorizeHttpRequests(request ->
                request.requestMatchers("/api/procLogin").permitAll()
                        .anyRequest().authenticated()
            )
        ;
        return http.build();
    }
}
