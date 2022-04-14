package com.company.service;

import com.company.dto.UrlDto;

/**
 * @author pushkar.chauhan@wissen.com
 */
public interface UrlMongoService {
    UrlDto createShortUrl(UrlDto urlDto);
    UrlDto getLongUrl(String urlId);
}