package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pushkar.chauhan@wissen.com
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UrlRequestException extends RuntimeException {

    public UrlRequestException(String message) {
        super(message);
    }

}