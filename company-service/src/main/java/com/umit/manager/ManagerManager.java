package com.umit.manager;

import com.umit.dto.response.ManagerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:9095/dev/v1/manager", name = "company-manager")
public interface ManagerManager {

    @GetMapping("/find-by-token")
    ResponseEntity<ManagerResponseDto> findByToken(@RequestParam String token);

}
