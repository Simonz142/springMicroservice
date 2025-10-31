package com.laura.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.laura.ProductService.model.ErrorResponse;

//this class will handle all the response from controller
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    //这里要返回给前端，所以返回值也要包装成一个response entity
    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception){
        return new ResponseEntity<>(new ErrorResponse().builder()
        .errorMessage(exception.getMessage())
        .errorCode(exception.getErrorCode())
        .build(),HttpStatus.NOT_FOUND);
    }
}
