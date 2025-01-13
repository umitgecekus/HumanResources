package com.umit.entity;

import com.umit.utility.enums.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Company extends BaseEntity{

    @Id
    private String id;
    private Long managerId;
    private String name;
    private String title;
    private String description;
    private String address;
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
    private EMemberShipPlan membershipPlan;
    private EStatus status;

}
