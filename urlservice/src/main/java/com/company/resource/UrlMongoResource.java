package com.company.resource;

import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import com.company.service.UrlMongoService;
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
@RequestMapping("/api/v1")
@Slf4j
public class UrlMongoResource implements UrlResource {

    private final UrlValidatorImpl urlValidatorImpl;
    private final UrlMongoService urlMongoService;

    public UrlMongoResource(
            UrlValidatorImpl urlValidatorImpl,
            UrlMongoService urlMongoService
    ) {
        this.urlValidatorImpl = urlValidatorImpl;
        this.urlMongoService = urlMongoService;
    }

    /**
     * @param urlDto urlName passed by user in HTTP Request Body
     * @return urlId corresponding to passed urlName
     */
    @PostMapping("/urls")
    @Override
    public ResponseEntity<UrlDto> createShortUrl(@RequestBody UrlDto urlDto) {
        if (!urlValidatorImpl.validatePostRequestInputParameters(urlDto)) {
            throw new UrlRequestException("Bad Request Exception is thrown for url : " + urlDto);
        }
        UrlDto shortUrl = urlMongoService.createShortUrl(urlDto);
        log.info("URL retrieved from redis service : {}", shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    /**
     * @param urlId urlId passed by user
     * @return void
     */

    @GetMapping("/urls/{urlId}")
    @Override
    public ResponseEntity<Void> getLongUrl(@PathVariable String urlId) {
        UrlDto longUrlDetails = new UrlDto();
        UrlDto urlDetailsDto = UrlDto.builder().urlId(urlId).build();
        if (urlValidatorImpl.validateGetRequestInputParameters(urlDetailsDto)) {
            longUrlDetails = urlMongoService.getLongUrl(urlId);
            log.info("URL Details retrieved from service : {}", longUrlDetails);
        }
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longUrlDetails.getUrlName())).build();
    }

}