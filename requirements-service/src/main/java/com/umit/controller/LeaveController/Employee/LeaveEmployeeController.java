package com.umit.controller.LeaveController.Employee;

import com.umit.dto.request.RequestLeaveDto;
import com.umit.dto.response.BasicResponse;
import com.umit.entity.Leave;
import com.umit.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umit.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class LeaveEmployeeController {

    private final LeaveService leaveService;

    /**
     * Employee kendi izinlerini buradan bulabilir
     * @param token
     * @return
     */

    @GetMapping(FIND_ALL_MY_LEAVES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Leave>>> findAllMyLeaves(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<Leave>>builder()
                .status(200)
                .message("Leaves are successfully found.")
                .data(leaveService.findAllMyLeavesForEmployee(token))
                .build());
    }

    @PutMapping(REQUEST_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> requestLeave(@RequestBody RequestLeaveDto dto) {
        leaveService.requestLeaveFromEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave requested by employee successfully.")
                .data(true)
                .build());
    }

}
