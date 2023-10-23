package com.sbs.kakao.base.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security를 사용하기 위해 웹 보안 활성화
@EnableMethodSecurity // 메소드 수준의 보안 활성화
@RequiredArgsConstructor // 생성자 자동 생성
public class SecurityConfig {
    @Bean // 메서드가 빈으로 등록될 수 있도록 함
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http // httpSecurity 객체를 사용하여 보안 구성 정의
                .formLogin( // 폼 로그인 구성
                        formLogin -> formLogin
                                .loginPage("/templates/user/member/login") // 로그인 페이지 지정
                )
                .oauth2Login( // OAuth2로그인 구성
                        oauth2Login -> oauth2Login
                                .loginPage("/templates/user/member/login") // 로그인 페이지 구성
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/templates/user/member/logout") // 로그아웃 URL 지정
                );

        return http.build(); // 구성된 httpSecurity 반환
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPaswwordEncoder를 사용하여 PasswordEncoder 반환
    }
}
