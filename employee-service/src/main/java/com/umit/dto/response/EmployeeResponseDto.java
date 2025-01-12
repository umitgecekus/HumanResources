package com.umit.dto.response;

import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String surname;
    private Long authId;
    private Long managerId;
    private String companyName;
    private String identityNumber;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String jobStartDate;
    private String jobEndDate;
    private String position;
    private Double salary;
    private String department;
    private String occupation;
    private String gender;
    private Boolean militaryService;
    private String driverLicense;
    private String avatar;
    private String shiftId;
    private EStatus status;

}
