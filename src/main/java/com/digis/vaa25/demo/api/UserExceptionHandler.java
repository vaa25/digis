package com.digis.vaa25.demo.api;

import com.digis.vaa25.demo.exception.UserAlreadyExists;
import com.digis.vaa25.demo.exception.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public final class UserExceptionHandler {

    @ExceptionHandler({UserNotFound.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not exists")
    public void handleUserNotFound() {

    }

    @ExceptionHandler({UserAlreadyExists.class})
    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "User with same login already exists")
    public void handleUserAlreadyExists() {

    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User login should not be null")
    public void handleIllegalArgumentException() {

    }

}
