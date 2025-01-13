package com.umit.controller.LeaveController.Manager;

import com.umit.dto.request.AddLeaveRequestDto;
import com.umit.dto.request.BaseRequestForRequirementsDto;
import com.umit.dto.response.BasicResponse;
import com.umit.entity.Leave;
import com.umit.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umit.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class LeaveManagerController {

    private final LeaveService leaveService;

    /**
     * Manager, employee için izin ekleyebilecek.
     * @param dto
     * @return
     */
    @PostMapping(ADD_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> addLeaveToEmployee(@RequestBody AddLeaveRequestDto dto){
        leaveService.addLeaveToEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave added successfully to employee from manager.")
                .data(true)
                .build());
    }

    /**
     * Manager, çalışanın aldığı izini onaylayabilecek
     * @param dto
     * @return
     */
    @PutMapping(APPROVE_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> approveLeave(@RequestBody BaseRequestForRequirementsDto dto){
        leaveService.approveLeaveForEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave approved.")
                .data(true)
                .build());
    }

    /**
     * Manager, employee için izinleri reddedebilecek.
     * @param dto
     * @return
     */
    @PutMapping(REJECT_LEAVE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> rejectLeaveOfEmployee(@RequestBody BaseRequestForRequirementsDto dto){
        leaveService.rejectLeaveForEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Leave rejected")
                .data(true)
                .build());
    }

    /**
     * Manager, bir çalışana ait tüm izinleri görebilecek.
     * @param token
     * @param employeeId
     * @return
     */
    @GetMapping(GET_ALL_LEAVES_OF_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Leave>>> getAllLeavesOfEmployee(@RequestParam String token, @RequestParam Long employeeId){
        return ResponseEntity.ok(BasicResponse.<List<Leave>>builder()
                .status(200)
                .message("Leaves for an employee are found.")
                .data(leaveService.findAllLeavesOfAnEmployee(token,employeeId))
                .build());
    }

    /**
     * Manager, çalışanlarına ait tüm bekleyen izinleri görebilecek
     * @param token
     * @return
     */
    @GetMapping(GET_ALL_PENDING_LEAVES_OF_EMPLOYEES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Leave>>> getAllPendingLeavesOfEmployees(@RequestParam String token){
        return ResponseEntity.ok(BasicResponse.<List<Leave>>builder()
                .status(200)
                .message("Pending leaves for manager are found.")
                .data(leaveService.findAllPendingLeavesOfEmployees(token))
                .build());
    }

}
