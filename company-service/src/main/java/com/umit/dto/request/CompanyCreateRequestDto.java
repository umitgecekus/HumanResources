package com.umit.dto.request;

import com.umit.utility.enums.EStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateRequestDto {

    @Id
    private String id;
    private Long managerId;
    private String name;
    private String title;
    private String description;
    private String address;
    @Size(min = 11, max = 14)
    private String phone;
    @Email
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
    private String membershipPlan;
    private EStatus status;
    private Long createAt;
    private Long updateAt;

}
