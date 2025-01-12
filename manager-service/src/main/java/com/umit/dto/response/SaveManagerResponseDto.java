package com.umit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveManagerResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String company;
    private String taxNumber;

}
