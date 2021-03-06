package com.company.resource;

import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import com.company.helper.UrlHelper;
import com.company.response.UrlResponse;
import com.company.service.UrlMongoService;
import com.company.validator.UrlValidatorImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author pushkar.chauhan@wissen.com
 */
@CrossOrigin(origins = {"http://localhost:9021","http://localhost:8765"})
@RestController
@Slf4j
public class UrlKafkaResource {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UrlValidatorImpl urlValidatorImpl;
    private final UrlMongoService urlMongoService;

    public UrlKafkaResource(
            KafkaTemplate<String, String> kafkaTemplate,
            UrlValidatorImpl urlValidatorImpl,
            UrlMongoService urlMongoService
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.urlValidatorImpl = urlValidatorImpl;
        this.urlMongoService = urlMongoService;
    }

    /**
     * @param urlDto urlName passed by user in HTTP Request Body
     * @return urlId corresponding to passed urlName
     */
    @PostMapping("/urls")
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody UrlDto urlDto) {
        if (!urlValidatorImpl.validatePostRequestInputParameters(urlDto)) {
            throw new UrlRequestException("Bad Request Exception is thrown for url : " + urlDto);
        }
        kafkaTemplate.send("url", new Gson().toJson(urlDto));
        UrlDto shortUrl = urlMongoService.createShortUrl(urlDto);
        log.info("URL retrieved from kafka : {}", shortUrl);
        UrlResponse urlResponse = UrlHelper.mapDtoToResponse(shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlResponse);
    }

    /**
     * @param urlId urlId passed by user
     * @return void
     */
    @GetMapping("/urls/{urlId}")
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