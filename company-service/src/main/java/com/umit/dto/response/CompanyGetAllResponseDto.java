package com.umit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyGetAllResponseDto {

    private String id;
    private String name;
    private String taxNumber;
    private String address;
    private String phone;
    private String email;
    private String sector;
    private String employeeCount;

}
