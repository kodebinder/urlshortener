package com.company.resource;

import com.company.dto.UrlDto;
import org.springframework.http.ResponseEntity;

/**
 * @author pushkar.chauhan@wissen.com
 */
public interface UrlResource {
    ResponseEntity<UrlDto> createShortUrl(UrlDto urlDto);
    ResponseEntity<Void> getLongUrl(String urlId);
}

