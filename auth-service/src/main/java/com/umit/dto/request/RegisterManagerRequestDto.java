package com.umit.dto.request;

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
public class RegisterManagerRequestDto {

    @Column(length = 50, nullable = false)
    private String name;
    @NotNull
    @Column(length = 50)
    private String surname;
    @Email(message = "Lutfen gecerli bir email adresi giriniz.")
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    private String avatar;
    @NotNull
    private String company;
    @NotNull
    @Column(unique = true)
    private String taxNumber;

}
