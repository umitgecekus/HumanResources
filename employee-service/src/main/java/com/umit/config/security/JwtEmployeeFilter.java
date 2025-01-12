package com.umit.config.security;

import com.umit.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JwtEmployeeFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenManager jwtTokenManager;
    @Autowired
    private JwtUserDetail employeeDetail;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        final String authHeader=request.getHeader("Authorization");

        log.info("gelen token...: "  + authHeader);
        log.info("Tüm istekler buraya düşecek ve burada auth kontrol yapılacak.");
        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            log.info("Token...: " + token);

            Optional<Long> id=jwtTokenManager.getIdFromToken(token);

            if (id.isPresent()){
                UserDetails userDetails= employeeDetail.getAuthById(id.get());
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }

}
