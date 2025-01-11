package com.umit.exception;

import lombok.Getter;

@Getter //errortype'a erisebilmek icin getter ekledik
public class AdminServiceException extends RuntimeException {
    /**
     * Kendi Exp sınıfımızı oluşturmak için yapılacaklar;
     * 1- Runtime Exp miras alınır
     * 2- Error.md'ye olası hatalar yazılır.
     * 3- ErrorType enumu yaratılır
     * 4- ErrorMEssage classı yaratılır.
     * 5- controllerda  hata-olustur mapi oluşturulur.
     */

    private final ErrorType errorType;

    public AdminServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AdminServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
