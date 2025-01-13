package com.umit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.umit.exception.ErrorType.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> globalHandler(RuntimeException runtimeException) {

        return new ResponseEntity<>(createErrorMessage(runtimeException,ErrorType.INTERNAL_ERROR)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommentServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> managerServiceHandler(CommentServiceException commentServiceException){
        return new ResponseEntity<>(createErrorMessage(commentServiceException,commentServiceException.getErrorType()),
                commentServiceException.getErrorType().getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createErrorMessage(exception,errorType);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }

    /**
     * Hata yakalama islemleri bir cok hata icin ayri ayri yapilmalidir. bu nedenle tum hatalarin
     * icerisine log alma islemi yazmak zorunda kaliriz. bu islemleri tekellestirmek ve hata log kayitlarini
     * toplamak icin tekbir method kullanmak daha dogru olacaktir.
     */
    private ErrorMessage createErrorMessage(Exception exception,ErrorType errorType){
        System.out.println("Tum hatalarin gectiği nokta...: "+ exception.getMessage());
        return ErrorMessage.builder()
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build();
    }

}
