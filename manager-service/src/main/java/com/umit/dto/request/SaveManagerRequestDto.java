package com.umit.dto.request;

import com.umit.utility.enums.ERole;
import com.umit.utility.enums.EStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveManagerRequestDto {

    private Long id;
    private Long authId;
    private String token;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String company;
    private String avatar;
    @NotNull
    @Column(unique = true)
    private String taxNumber;
    private ERole role;
    private Long createAt;
    private EStatus status;

}
