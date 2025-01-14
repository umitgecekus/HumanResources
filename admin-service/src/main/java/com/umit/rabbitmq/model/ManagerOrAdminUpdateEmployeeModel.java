package com.umit.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerOrAdminUpdateEmployeeModel implements Serializable {

    private Long id;
    private String token;
    private String name;
    private String surname;
    private String birthDate;
    private Long managerId;
    private String phoneNumber;
    private String address;
    private String jobStartDate;
    private String jobEndDate;
    private String position;
    private String salary;
    private String department;
    private String occupation;
    private String avatar;
    private String shiftId;

}
