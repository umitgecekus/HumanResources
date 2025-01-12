package com.umit.controller;

import com.umit.dto.request.AdminUpdateManagerRequestDto;
import com.umit.dto.request.SaveManagerRequestDto;
import com.umit.dto.request.UpdateManagerRequestDto;
import com.umit.dto.response.BasicResponse;
import com.umit.dto.response.ManagerResponseDto;
import com.umit.dto.response.SaveManagerResponseDto;
import com.umit.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.umit.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(MANAGER)
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping(SAVE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<SaveManagerResponseDto>> createManager(@RequestBody SaveManagerRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<SaveManagerResponseDto>builder()
                .status(200)
                .message("Manager created")
                .data(managerService.createManager(dto))
                .build());
    }

    /**
     * Manager kendi bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(UPDATE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateManager(@RequestBody UpdateManagerRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager updated successfully")
                .data(managerService.updateManager(dto))
                .build()
        );
    }

    /**
     * Admin manager bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(ADMIN_UPDATE_MANAGER)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> adminUpdateManager(@RequestBody AdminUpdateManagerRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager updated successfully")
                .data(managerService.adminUpdateManager(dto))
                .build()
        );
    }

    @GetMapping("/find-by-token")
    public ResponseEntity<ManagerResponseDto> findByToken(@RequestParam String token){
        System.out.println("manager controller buraya geliyor mu ???");
        System.out.println("token = " + token);
        return ResponseEntity.ok(managerService.findByToken(token));
    }


}
