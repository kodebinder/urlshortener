package com.company.service;

import com.company.dto.UrlDto;
import com.company.entity.Url;
import com.company.exception.UrlRequestException;
import com.company.helper.UrlHelper;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Service
@Slf4j
public class UrlRedisServiceImpl implements UrlRedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public UrlRedisServiceImpl(
            StringRedisTemplate stringRedisTemplate
    ) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     *
     * @param urlDto urlDto passed by user in HTTP Request Body
     * @return UrlDto
     */
    @Transactional
    @Override
    public UrlDto createShortUrl(UrlDto urlDto) {
        UrlValidator urlValidator = new UrlValidator(new String[]{
                "http",
                "https"
        });

        if (urlValidator.isValid(urlDto.getUrlName())) {
            String urlId = Hashing.murmur3_32_fixed().hashString(urlDto.getUrlName(), StandardCharsets.UTF_8).toString();
            log.info("URL Id generated : {}", urlId);

            stringRedisTemplate.opsForValue().set(urlId, urlDto.getUrlName());

            urlDto.setUrlId(urlId);

            Url url = UrlHelper.mapDtoToEntity(urlDto);

            log.info("Url saved to Redis : {}", url);

            urlDto = UrlHelper.mapEntityToDto(url);

            return urlDto;
        }

        throw new UrlRequestException("Invalid urlName : " + urlDto.getUrlName());
    }

    /**
     * @param urlId urlId passed by user
     * @return retrieved URL
     */
    @Override
    public UrlDto getLongUrl(@PathVariable String urlId) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String urlName = stringStringValueOperations.get(urlId);

        if (Objects.isNull(urlName)) {
            throw new UrlRequestException("There is no shorter URL for urlId : " + urlId);
        }

        log.info("URL Name Retrieved : {}", urlName);

        UrlDto urlDto = new UrlDto();
        urlDto.setUrlId(urlId);
        urlDto.setUrlName(urlName);

        Url url = UrlHelper.mapDtoToEntity(urlDto);

        log.info("Url from Redis : {}", url);

        return urlDto;
    }
}