package com.umit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponseDto {

    private Long id;
    private Long authId;
    private String companyId;
    private String name;
    private String surname;
    private String email;
    private String avatar;

}
