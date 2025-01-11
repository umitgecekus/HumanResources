package com.umit.controller;

import com.umit.dto.request.UpdateAdminRequestDto;
import com.umit.dto.response.BasicResponse;
import com.umit.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.umit.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {
    private final AdminService adminService;

    /**
     * Admin kendi bilgilerini guncellemek icin kullanilir
     */

    @PutMapping(UPDATE_ADMIN)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateAdmin(@RequestBody UpdateAdminRequestDto dto) {


        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Admin updated successfully")
                .data(adminService.updateAdmin(dto))
                .build()
        );
    }

}
