package com.umit.controller;

import com.umit.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.umit.dto.request.UpdateEmployeeRequestDto;
import com.umit.dto.response.BasicResponse;
import com.umit.dto.response.EmployeeResponseDto;
import com.umit.entity.Employee;
import com.umit.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.umit.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Employeenin kendi bilgilerini guncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> updateEmployee(@RequestBody UpdateEmployeeRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Employee updated successfully")
                .data(employeeService.updateEmployee(dto))
                .build()
        );
    }

    /**
     * Managerin veya adminin employee bilgilerini güncellemesi icin method
     * @param dto
     * @return
     */
    @PutMapping(MANAGER_OR_ADMIN_UPDATE_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> managerOrAdminUpdateEmployee(@RequestBody ManagerOrAdminUpdateEmployeeRequestDto dto) {

        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Manager updated to employee successfully")
                .data(employeeService.managerOrAdminUpdateEmployee(dto))
                .build()
        );
    }

    @GetMapping(GET_EMPLOYEES_BY_MANAGER_ID)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Employee>>> getEmployeesByManagerId(Long managerId) {

        return ResponseEntity.ok(BasicResponse.<List<Employee>>builder()
                .status(200)
                .message("Employees list turned successfully")
                .data(employeeService.getEmployeesByManagerId(managerId))
                .build()
        );
    }

    @GetMapping(FIND_EMPLOYEE_BY_TOKEN)
    public ResponseEntity<EmployeeResponseDto> findEmployeeByToken(@RequestParam String token) {
        return ResponseEntity.ok(employeeService.findEmployeeByToken(token));
    }

    @GetMapping(FIND_BY_ID)
    public ResponseEntity<EmployeeResponseDto> findById(@RequestParam Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }


    /**
     * RequirementsService ile feignclient ile haberlestirilen methodtur.
     * Managerin employeenin expensesleini onaylamak icin ihtiyac duydugu bir methodtur.
     * @param id
     * @return
     */
    @PutMapping(UPDATE_SALARY_EMPLOYEE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<EmployeeResponseDto>> updateEmployeeSalary(@RequestParam Long id, @RequestParam Double salary) {
        return ResponseEntity.ok(BasicResponse.<EmployeeResponseDto>builder()
                .status(200)
                .message("Employee salary updated successfully")
                .data(employeeService.updateEmployeeSalary(id, salary))
                .build()
        );
    }

}
