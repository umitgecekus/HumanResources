package com.umit.dto.request;

import com.umit.utility.enums.ELeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveRequestDto {

    private String token;
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private LocalDate startDate;
    private LocalDate endDate;
    private ELeaveType leaveType;
    private String document;

}
