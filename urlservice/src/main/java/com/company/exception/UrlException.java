package com.company.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Data
public class UrlException {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    public UrlException(
            String message,
            HttpStatus httpStatus,
            LocalDateTime localDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }

}
