package com.company.resource;

import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import com.company.service.UrlRedisService;
import com.company.validator.UrlValidatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author pushkar.chauhan@wissen.com
 */
@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api/v2")
@Slf4j
public class UrlRedisResource implements UrlResource {

    private final UrlValidatorImpl urlValidatorImpl;
    private final UrlRedisService urlRedisService;

    public UrlRedisResource(
            UrlValidatorImpl urlValidatorImpl,
            UrlRedisService urlRedisService
    ) {
        this.urlValidatorImpl = urlValidatorImpl;
        this.urlRedisService = urlRedisService;
    }

    /**
     * @param urlDto urlDto passed by user in HTTP Request Body
     * @return urlId corresponding to passed urlName
     */
    @PostMapping("/urls")
    @Override
    public ResponseEntity<UrlDto> createShortUrl(@RequestBody UrlDto urlDto) {
        if (!urlValidatorImpl.validatePostRequestInputParameters(urlDto)) {
            throw new UrlRequestException("Bad Request Exception is thrown for urlDto : " + urlDto);
        }
        UrlDto shortUrl = urlRedisService.createShortUrl(urlDto);
        log.info("URL retrieved from service : {}", shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    /**
     * @param urlId urlId passed by user
     * @return void
     */
    @GetMapping("/urls/{urlId}")
    @Override
    public ResponseEntity<Void> getLongUrl(@PathVariable String urlId) {
        UrlDto longUrl = new UrlDto();
        if (urlValidatorImpl.validateGetRequestInputParameters(UrlDto.builder().urlId(urlId).build())) {
            longUrl = urlRedisService.getLongUrl(urlId);
            log.info("URL retrieved from service : {}", longUrl);
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrl.getUrlName())).build();
    }

}