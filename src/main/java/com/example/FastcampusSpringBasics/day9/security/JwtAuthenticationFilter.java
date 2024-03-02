package com.example.FastcampusSpringBasics.day9.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request); // 토큰 꺼냄

        if (token != null) {
            // 토큰 검증 = 토큰을 보고, 인증된 객체 맞아?
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication); // 응 맞아
            // 시큐리티는 인증이 된 사용자만 허락해줄거니까, 얘 인증 되었어! 라고 필터에서 대신 말해주는 것
        }

        chain.doFilter(request, response);
    }
}
