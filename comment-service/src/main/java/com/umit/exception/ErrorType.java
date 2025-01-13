package com.umit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(2000, "Sunucuda beklenmeye hata oluştu, lütfen terar deneyiniz", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(2002,"Girdiğiniz token hatalıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.UNAUTHORIZED),
    MANAGER_ALREADY_HAVE_COMMENT(2003,"Bu kullanıcı zaten bir yorum ekledi.",HttpStatus.BAD_REQUEST),
    MANAGER_NOT_FOUND(2004,"Bu kullanıcı bulunamadı.",HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus httpStatus;

}
