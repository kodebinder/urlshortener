package com.company.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UrlExceptionHandlerTest {

    @Test
    void testHandleException() {
        UrlExceptionHandler urlExceptionHandler = new UrlExceptionHandler();
        ResponseEntity<Object> actualHandleExceptionResult = urlExceptionHandler
                .handleException(new UrlRequestException("https://example.org/example"));
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleExceptionResult.getStatusCode());
    }

}

