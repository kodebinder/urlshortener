package com.company.service;

import com.company.dto.UrlDto;
import com.company.entity.Url;
import com.company.exception.UrlRequestException;
import com.company.helper.UrlHelper;
import com.company.repository.UrlMongoRepository;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Service
@Slf4j
public class UrlMongoServiceImpl implements UrlMongoService {

    private final UrlMongoRepository urlMongoRepository;

    public UrlMongoServiceImpl(
            @Qualifier("urlMongoRepository") UrlMongoRepository urlMongoRepository
    ) {
        this.urlMongoRepository = urlMongoRepository;
    }

    /**
     * @param urlDto urlDto passed by user in HTTP Request Body
     * @return urlId corresponding to passed urlName
     */
    @Transactional
    @Override
    public UrlDto createShortUrl(UrlDto urlDto) {

        Url url = new Url();
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});

        if (urlValidator.isValid(urlDto.getUrlName())) {
            String urlId = Hashing.murmur3_32_fixed().hashString(urlDto.getUrlName(), StandardCharsets.UTF_8).toString();
            log.info("URL Id generated : {}", urlId);

            Optional<Url> optionalUrl = urlMongoRepository.findByUrlId(urlId);
            if (optionalUrl.isPresent()) {
                log.info("Fetching Url : {}", url);
                url = optionalUrl.get();
                log.info("Fetched Url : {}", url);
            } else {
                url.setId(new BigInteger(String.valueOf(urlMongoRepository.findAll().size() + 1)));
                url.setUrlId(urlId);
                url.setUrlName(urlDto.getUrlName());
                url.setUserName(urlDto.getUserName());
            }

            urlMongoRepository.save(url);
            log.info("Persisted Url : {}", url);

            return UrlHelper.mapEntityToDto(url);

        }

        throw new UrlRequestException("Invalid urlName : " + urlDto.getUrlName());
    }

    /**
     *
     * @param urlId urlId passed by user
     * @return UrlDto
     */
    @Override
    public UrlDto getLongUrl(@PathVariable String urlId) {

        Optional<Url> optionalUrl = urlMongoRepository.findByUrlId(urlId);

        if (!optionalUrl.isPresent()) {
            throw new UrlRequestException("There is no shorter URL for urlId : " + urlId);
        }

        return UrlHelper.mapEntityToDto(optionalUrl.get());
    }

}