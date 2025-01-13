package com.umit.manager;

import com.umit.dto.response.BasicResponse;
import com.umit.dto.response.EmployeeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.umit.utility.constants.RestApiUrls.*;

@FeignClient(url= "http://localhost:9094/dev/v1/employee", name= "requirements-employee")
public interface EmployeeManager {

    @GetMapping(FIND_EMPLOYEE_BY_TOKEN)
    ResponseEntity<EmployeeResponseDto> findEmployeeByToken(@RequestParam String token);

    @GetMapping("/find-by-id")
    ResponseEntity<EmployeeResponseDto> findById(@RequestParam Long id);

    @PutMapping(UPDATE_SALARY_EMPLOYEE)
    ResponseEntity<BasicResponse<EmployeeResponseDto>> updateEmployeeSalary(@RequestParam Long id, @RequestParam Double salary);

}
