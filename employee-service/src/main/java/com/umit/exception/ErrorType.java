package com.umit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(4000, "Sunucuda beklenmeye hata oluştu, lütfen terar deneyiniz", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(4001, "Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4002,"Girdiğiniz token hatalıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.UNAUTHORIZED),
    EMPLOYEE_NOT_FOUND(4003,"Employee bulunamadı",HttpStatus.INTERNAL_SERVER_ERROR),
    EMPLOYEE_ALREADY_EXISTS(4004,"Employee zaten kayıtlı",HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    int code;
    String message;
    HttpStatus httpStatus;

}
