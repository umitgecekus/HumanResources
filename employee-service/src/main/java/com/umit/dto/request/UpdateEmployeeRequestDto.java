package com.umit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDto {

    private Long id;
    private String token;
    private String name;
    private String surname;
    private String birthDate;
    private String phoneNumber;
    private String address;
    private String avatar;

}
