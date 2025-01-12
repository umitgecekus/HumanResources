package com.umit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(5000, "Sunucuda beklenmeye hata oluştu, lütfen terar deneyiniz", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(5001, "Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(5002, "Geçersiz token bilgisi girildi. Lütfen tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    MANAGER_NOT_FOUND(5003, "Bu id ile bir manager bulunamadı. Lütfen tekrar deneyiniz", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(5004,"Bu id ile bir kullanıcı bulunamadı. Lütfen tekrar deneyiniz" ,HttpStatus.BAD_REQUEST );

    int code;
    String message;
    HttpStatus httpStatus;
}
