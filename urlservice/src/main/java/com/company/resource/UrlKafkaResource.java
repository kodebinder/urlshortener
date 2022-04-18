package com.company.resource;

import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import com.company.service.UrlMongoService;
import com.company.validator.UrlValidatorImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author pushkar.chauhan@wissen.com
 */
@CrossOrigin(origins = "http://localhost:9021")
@RestController
@RequestMapping("/api/v3")
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
    public ResponseEntity<UrlDto> createShortUrl(@RequestBody UrlDto urlDto) {
        if (!urlValidatorImpl.validatePostRequestInputParameters(urlDto)) {
            throw new UrlRequestException("Bad Request Exception is thrown for url : " + urlDto);
        }
        kafkaTemplate.send("url", new Gson().toJson(urlDto));
        UrlDto shortUrl = urlMongoService.createShortUrl(urlDto);
        log.info("URL retrieved from redis service : {}", shortUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

}