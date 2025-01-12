package com.umit.entity;

import com.umit.utility.enums.EGender;
import com.umit.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phoneNumber;
    private String identityNumber;
    private String taxNumber;
    private String email;
    private String address;
    private String occupation;
    private String companyId;
    private EGender gender;
    private String jobStartDate;
    private Long createAt;
    private Long updateAt;
    private EStatus status;
}
