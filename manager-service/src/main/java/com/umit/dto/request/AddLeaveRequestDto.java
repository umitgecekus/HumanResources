package com.umit.dto.request;

import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveRequestDto {

    private String token;
    private Long managerId;
    private Long employeeId;
    private Long startDate;
    private Long endDate;
    private EStatus status;
    private Double numberOfDays;
    private String document;

}
