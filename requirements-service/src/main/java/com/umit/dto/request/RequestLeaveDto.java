package com.umit.dto.request;

import com.umit.utility.enums.EEmployeeChooseLeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLeaveDto {

    private String token;
    private LocalDate startDate;
    private LocalDate endDate;
    private EEmployeeChooseLeaveType leaveType;
    private String document;

}
