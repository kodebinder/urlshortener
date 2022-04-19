package com.company.helper;

import com.company.dto.UrlDto;
import com.company.entity.Url;
import com.company.response.UrlResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Component
@Slf4j
public class UrlHelper {

    public UrlHelper() {
    }

    /**
     * Mapping Entity to DTO
     *
     * @param url url
     * @return UrlDto
     */
    public static UrlDto mapEntityToDto(Url url) {
        log.info("Mapping Url Entity to UrlDto");
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(url, UrlDto.class);
    }

    /**
     * Mapping DTO to Entity
     *
     * @param urlDto urlDto
     * @return Url
     */
    public static Url mapDtoToEntity(UrlDto urlDto) {
        log.info("Mapping UrlDto to Url");
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(urlDto, Url.class);
    }

    /**
     * Mapping DTO to Response
     *
     * @param urlDto urlDto
     * @return UrlResponse
     */
    public static UrlResponse mapDtoToResponse(UrlDto urlDto) {
        UrlResponse urlResponse = new UrlResponse();
        urlResponse.setId(urlDto.getId());
        urlResponse.setUrlId(urlDto.getUrlId());
        urlResponse.setUrlName(urlDto.getUrlName());
        urlResponse.setUserName(urlDto.getUserName());
        return urlResponse;
    }

}