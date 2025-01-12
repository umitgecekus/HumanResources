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
public class SaveEmployeeRequestDto {

    private Long authId;
    private Long managerId;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String position;
    private String department;
    private String occupation;
    private String companyName;
    private Double salary;
    private EStatus status;
    private Long jobStartDate;
    private Long createAt;
    private Long updateAt;

}
