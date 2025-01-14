package com.umit.rabbitmq.model;

import com.umit.utility.enums.EStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateManagerModel implements Serializable {

    @Id
    private Long id;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phone;
    private String identityNumber;
    private String taxNumber;
    private String email;
    private String password;
    private String address;
    private Long occupation;
    private String companyId;
    private String gender;
    private String jobStartDate;
    private Long createAt;
    private Long updateAt;
    private EStatus status;

}
