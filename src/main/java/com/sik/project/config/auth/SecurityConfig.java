/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sik.project.config.auth;

import com.sik.project.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면을 사용하기 위해 해당 옵션들을 비활성화 처리
                .and()
                    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점. 해당 라인이 선언되어야만 antMatchers 옵션을 활성화 가능.
                    .antMatchers("/", "/css/**", "/images/**",  //권한 관리 대상을 지정하는 옵션. URL,HTTP 메소드 별로 관리 가능. "/"등 지정된 URL은 permitAll()로 전체 열람 가능. POST 메소드
                            //이면서 "api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능.
                            "/js/**", "h2-console/**").permitAll().antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()   //anyRequest : 설정된 값들 이외 나머지 URL에 authenticated를 추가해여 나머지 URL은 인증된 사용자 즉, 로그인한 사용자에게만 허용.
                .and()
                    .logout() //로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공시 "/"로 이동.
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() //로그인 기능에 대한 설정
                        .userInfoEndpoint() //로그인 성공한 이후 사용자 정보를 가져올때 설정
                            .userService(customOAuth2UserService);  //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록. 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를
                            //정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능.
    }

}
