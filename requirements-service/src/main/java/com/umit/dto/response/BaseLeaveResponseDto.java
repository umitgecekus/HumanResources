package com.umit.dto.response;

import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseLeaveResponseDto {

    private Long id;
    private Long employeeId;
    private Long authId;
    private Long managerId;
    private String companyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long approvalDate;
    private EStatus status;
    private Double numberOfDays;
    private String document;

}
