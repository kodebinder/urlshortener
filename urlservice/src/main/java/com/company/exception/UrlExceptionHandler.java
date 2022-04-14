package com.company.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * This class acts as a global exception handler
 *
 * @author pushkar.chauhan@wissen.com
 */
@ControllerAdvice
@Slf4j
public class UrlExceptionHandler {

    @ExceptionHandler(value = {UrlRequestException.class})
    public ResponseEntity<Object> handleException(UrlRequestException ex) {
        log.error(ex.getMessage(), ex);

        UrlException urlShortenerException = new UrlException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(urlShortenerException, HttpStatus.BAD_REQUEST);
    }

}
