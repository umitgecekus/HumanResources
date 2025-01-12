package com.umit.rabbitmq.model;

import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel implements Serializable {

    private Long authId;
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
    private EStatus status;
    private Long createAt;
    private Long updateAt;
    private Long managerId;
    private Long jobStartDate;

}
