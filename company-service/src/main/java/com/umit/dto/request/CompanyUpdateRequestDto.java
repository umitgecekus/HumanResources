package com.umit.dto.request;

import com.umit.utility.enums.EMemberShipPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateRequestDto {

    private String id;
    private String token;
    private Long managerId;
    private String name;
    private String title;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String logo;
    private String sector;
    private String taxNumber;
    private String taxOffice;
    private String mersisNo;
    private String vision;
    private String mission;
    private String country;
    private String city;
    private String employeeCount;
    private String founded;
    private String foundingYear;
    private String linkedin;
    private EMemberShipPlan membershipPlan;

}
