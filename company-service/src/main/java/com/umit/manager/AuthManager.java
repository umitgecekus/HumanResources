package com.umit.manager;

import com.umit.dto.response.AuthResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9091/dev/v1/auth", name = "auth-manager")
public interface AuthManager {

    @GetMapping("/find-by-token")
    @CrossOrigin("*")
    ResponseEntity<AuthResponseDto> findByToken(@RequestParam("token") String token);

}
