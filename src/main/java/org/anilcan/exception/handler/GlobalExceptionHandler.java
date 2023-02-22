package org.anilcan.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.anilcan.exception.base.BaseException;
import org.anilcan.model.response.ErrorResponseModel;
import org.anilcan.utility.enums.ExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    private ResponseEntity<ErrorResponseModel> handle(BaseException exception) {

        log.error("Error occurred: ",exception);

        return new ResponseEntity<>(new ErrorResponseModel(exception.getExceptionMessage(), LocalDateTime.now()),
                exception.getExceptionMessage().getErrorCode());

    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponseModel> handle(Exception exception) {

        log.error("Error occurred: ",exception);

        return new ResponseEntity<>(new ErrorResponseModel(ExceptionMessage.UNKNOWN_EXCEPTION, LocalDateTime.now()),
                ExceptionMessage.UNKNOWN_EXCEPTION.getErrorCode());

    }
}
