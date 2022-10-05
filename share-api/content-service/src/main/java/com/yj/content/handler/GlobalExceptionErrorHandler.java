package com.yj.content.handler;

import com.yj.content.domain.entity.ErrorBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionErrorHandler {
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity error(SecurityException e) {
        log.warn("发生 SecurityException 异常", e);
        return new ResponseEntity<>(ErrorBody.builder()
                .body(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value()).build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>(
                ErrorBody.builder()
                        .body(e.getMessage())
                        .status(HttpStatus.OK.value())
                        .build(),
                HttpStatus.OK
        );
    }
}
