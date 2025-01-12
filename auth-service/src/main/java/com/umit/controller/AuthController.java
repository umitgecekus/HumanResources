package com.umit.controller;
import com.umit.dto.request.*;
import com.umit.dto.response.AuthLoginResponseDto;
import com.umit.dto.response.AuthResponseDto;
import com.umit.dto.response.BasicResponse;
import com.umit.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.umit.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
@Slf4j
public class AuthController {

    private final AuthService authService;


    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<AuthLoginResponseDto>> login(@RequestBody @Valid AuthLoginRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<AuthLoginResponseDto>builder()
                .status(200)
                .message("Login successful")
                .data(authService.login(dto))
                .build());
    }

    @PostMapping(REGISTER_ADMIN)
    @CrossOrigin("*")
//    @PreAuthorize("hasAuthority-hasRole???('ADMIN')")
    public ResponseEntity<BasicResponse<Boolean>> registerAdmin(@RequestBody @Valid RegisterAdminRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Admin Register successful")
                .data(authService.registerAdmin(dto))
                .build());
    }

    @PostMapping(REGISTER_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> registerManager(@RequestBody @Valid RegisterManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager Register successful")
                .data(authService.registerManager(dto))
                .build());
    }

    @PostMapping(REGISTER_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> registerEmployee(@RequestBody @Valid RegisterEmployeeRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee Register successful")
                .data(authService.registerEmployee(dto))
                .build());
    }

    @PutMapping(CHANGE_PASSWORD)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> changePassword(@RequestBody @Valid ChangePasswordDto dto) {
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Password changed successfully")
                .data(authService.changePassword(dto))
                .build());
    }

    @GetMapping("/find-by-token")
    @CrossOrigin("*")
    public ResponseEntity<AuthResponseDto> findByToken(@RequestParam("token") String token){
        System.out.println("auth controller buraya geliyor mu ???");
        System.out.println("token = " + token);
        log.info("Received request to find by token: {}" + token);
        return ResponseEntity.ok(authService.findByToken(token));
    }

}
