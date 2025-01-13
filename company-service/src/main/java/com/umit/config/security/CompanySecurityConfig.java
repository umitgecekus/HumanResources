package com.umit.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@Slf4j
public class CompanySecurityConfig {

    @Bean
    public JwtCompanyFilter getJwtManagerFilter(){
        return new JwtCompanyFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(req ->
                        req.requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**"
                                ).permitAll()
                                .requestMatchers("/dev/v1/company/**").permitAll()
                                .requestMatchers("/dev/v1/manager/**").permitAll()
//                        .requestMatchers("/dev/v1/company/**").hasRole("MANAGER")
                                .anyRequest()
                                .authenticated()
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());

        log.info("**** Tüm istekler buradan geçecek *****");
        httpSecurity.addFilterBefore(getJwtManagerFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
