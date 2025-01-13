package com.umit.exception;

import lombok.Getter;

@Getter
public class RequirementsServiceException extends RuntimeException{

    private final ErrorType errorType;

    public RequirementsServiceException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType = errorType;
    }
    public RequirementsServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
