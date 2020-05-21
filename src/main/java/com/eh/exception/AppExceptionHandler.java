package com.eh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

@RestControllerAdvice
public class AppExceptionHandler {

    /**
     *  For Spring Boot version upto 2.2.X
     * @param exception
     * @return
     */
    @ExceptionHandler(value = WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleWebExchangeBindException(WebExchangeBindException exception){
        return ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .status(HttpStatus.BAD_REQUEST.toString())
                .message(CollectionUtils.isEmpty(
                        exception.getAllErrors()) ?
                        exception.getMessage() :
                        exception.getAllErrors().get(0).getDefaultMessage())
                .build();
    }

    /**
     * For Spring Boot version upto 2.2.X
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ServerWebInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleServerWebInputException(ServerWebInputException exception){
        return ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .status(HttpStatus.BAD_REQUEST.toString())
                .message(exception.getMessage())
                .build();
    }

    /**
     *  Form Spring Boot version upto 2.3.X
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleWebExchangeBindException(MethodArgumentNotValidException exception){
        return ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .status(HttpStatus.BAD_REQUEST.toString())
                .message(CollectionUtils.isEmpty(
                        exception.getBindingResult().getAllErrors()) ?
                        exception.getMessage() :
                        exception.getBindingResult().getAllErrors()
                                .get(0).getDefaultMessage())
                .build();
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        String[] errs = exception.getMessage().split(";");
        return ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .status(HttpStatus.BAD_REQUEST.toString())
                .message((errs != null && errs.length > 0)
                        ? errs[errs.length - 1]
                        : exception.getMessage())
                .build();
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception){
        return ErrorResponse.builder()
                .errorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message(exception.getMessage())
                .build();
    }

}
