package com.umit.dto.request;

import com.umit.utility.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateManagerRequestDto {

    private String token;
    private Long id;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phoneNumber;
    private String taxNumber;
    private String address;
    private EGender gender;
    private String occupation;

}
