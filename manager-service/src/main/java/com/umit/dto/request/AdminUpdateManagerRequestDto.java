package com.umit.dto.request;

import com.umit.utility.enums.EGender;
import com.umit.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateManagerRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phone;
    private String taxNumber;
    private String email;
    private String password;
    private String address;
    private String occupation;
    private String companyId;
    private EGender gender;
    private String jobStartDate;
    private Long updateAt;
    private EStatus status;

}
