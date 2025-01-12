package com.umit.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@Slf4j
public class AdminSecurityConfig {

    @Bean
    public JwtAdminFilter getJwtAdminFilter(){
        return new JwtAdminFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(req ->
                        req.requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .requestMatchers("/dev/v1/admin/**").permitAll()
//                        .requestMatchers("/dev/v1/auth/register-admin").hasAuthority("ADMIN")
//                        .requestMatchers("/dev/v1/auth/register-manager/**").hasAuthority("MANAGER")
//                        .requestMatchers("/dev/v1/auth/register-employee/**").hasAuthority("MANAGER")
                                .anyRequest()
                                .authenticated()
        );



        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        log.info("**** Tüm istekler buradan geçecek *****");

        httpSecurity.addFilterBefore(getJwtAdminFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
