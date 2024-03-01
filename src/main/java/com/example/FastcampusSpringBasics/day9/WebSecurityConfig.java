package com.example.FastcampusSpringBasics.day9;

import com.example.FastcampusSpringBasics.day9.security.JwtAuthenticationFilter;
import com.example.FastcampusSpringBasics.day9.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 1) 인증(authentication) : 로그인
 *    # 쿠키 vs 세션 vs JWT
 *    (1) 쿠키 : 보안 문제
 *    (2) 세션 : 금고
 *    (3) JWT "암호화" : // 유저 로그인
 *                     // -> 로그인 성공 JWT 생성 & 회신
 *                     -> 유저는 요청할 때마다 JWT를 같이 줘요.
 *                     -> JWT를 복호화(decoding)
 *
 * 2) 인가(authorization) : 권한
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers("/", "/home", "/join", "/login")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .csrf().disable()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider()),
                UsernamePasswordAuthenticationFilter.class);

        return http.getOrBuild();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("songa")
//                        .password("123")
//                        .roles("ADMIN")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
