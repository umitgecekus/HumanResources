package com.umit.dto.response;

import com.umit.utility.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {

    private Long id;
    private ERole role;
    private EStatus status;
    private Long updateAt;

}
