package com.umit.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequestDto {

    @Email(message = "Lutfen gecerli bir email adresi giriniz.")
    @NotNull(message = "Email alanı zorunludur.")
    private String email;

    //TODO: validation dependencies nerede?
    @Size(min=8, max=20, message = "Sifre uzunlugu 8 ile 20 arasinda olmalidir.")
    @NotNull(message = "Sifre alanı zorunludur.")
    private String password;

}
