package com.company.service;

import com.company.constant.UrlConstants;
import com.company.dto.UrlDto;
import com.company.entity.Url;
import com.company.exception.UrlRequestException;
import com.company.repository.UrlMongoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@ContextConfiguration(classes = {UrlMongoServiceImpl.class})
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UrlMongoServiceImplTest {

    @Mock
    private UrlMongoRepository urlMongoRepository;

    @InjectMocks
    private UrlMongoServiceImpl urlMongoServiceImpl;

    @Test
    void test_createUrl_forNewUrl() {
        UrlDto urlDto = urlMongoServiceImpl.createShortUrl(getValidMockUserDto());
        Assertions.assertThat(urlDto).isNotNull();
        Assertions.assertThat(urlDto.getUrlName()).isEqualTo(String.valueOf(UrlConstants.URL_NAME));
    }

    @Test
    void test_createUrl_forExistingUrl() {
        Mockito.when(urlMongoRepository.findByUrlId(Mockito.anyString())).thenReturn(Optional.ofNullable(getValidMockUser()));
        UrlDto urlDto = urlMongoServiceImpl.createShortUrl(getValidMockUserDto());
        Assertions.assertThat(urlDto).isNotNull();
        Assertions.assertThat(urlDto.getUrlName()).isEqualTo(String.valueOf(UrlConstants.URL_NAME));
        Mockito.verify(urlMongoRepository, Mockito.times(1)).findByUrlId(Mockito.anyString());
    }

    @Test
    void testCreate_shouldThrowBadArgumentsExceptionWhenEmptyUrlIsPassed() {
        Assertions.assertThatThrownBy(() -> urlMongoServiceImpl.createShortUrl(getInvalidMockUserDto()))
                .isInstanceOf(UrlRequestException.class)
                .hasMessageContaining("Invalid urlName : " + getInvalidMockUser().getUrlName());
    }

    @Test
    void test_getLongUrl_ForExistingUrl() {
        Mockito.when(urlMongoRepository.findByUrlId(Mockito.anyString())).thenReturn(Optional.ofNullable(getValidMockUser()));
        UrlDto urlDto = urlMongoServiceImpl.getLongUrl(getValidMockUser().getUrlId());
        Assertions.assertThat(urlDto).isNotNull();
        Mockito.verify(urlMongoRepository, Mockito.times(1)).findByUrlId(Mockito.anyString());
    }

    @Test
    void testGetUrl_shouldThrowBadArgumentsExceptionWhenEmptyUrlIdIsPassed() {
        Assertions.assertThatThrownBy(() -> urlMongoServiceImpl.getLongUrl(""))
                .isInstanceOf(UrlRequestException.class)
                .hasMessageContaining("There is no shorter URL for urlId : " + getInvalidMockUser().getUrlId());
    }

    @Test
    void testGetUrl_shouldThrowBadArgumentsExceptionWhenNullUrlIdIsPassed() {
        Assertions.assertThatThrownBy(() -> urlMongoServiceImpl.getLongUrl(getValidMockUser().getUrlId()))
                .isInstanceOf(UrlRequestException.class)
                .hasMessageContaining("There is no shorter URL for urlId : " + getInvalidMockUser().getUrlId());
    }

    private UrlDto getValidMockUserDto() {
        return UrlDto.builder()
                .urlId(String.valueOf(UrlConstants.URL_ID))
                .urlName(String.valueOf(UrlConstants.URL_NAME))
                .email(String.valueOf(UrlConstants.EMAIL))
                .userName(String.valueOf(UrlConstants.USERNAME))
                .password(String.valueOf(UrlConstants.PASSWORD))
                .phoneNumber(String.valueOf(UrlConstants.PHONE_NUMBER))
                .build();
    }

    private UrlDto getInvalidMockUserDto() {
        return UrlDto.builder()
                .urlId("")
                .urlName(null)
                .email(String.valueOf(UrlConstants.EMAIL))
                .userName(String.valueOf(UrlConstants.USERNAME))
                .password(String.valueOf(UrlConstants.PASSWORD))
                .phoneNumber(String.valueOf(UrlConstants.PHONE_NUMBER))
                .build();
    }

    private Url getValidMockUser() {
        return Url.builder()
                .urlId(String.valueOf(UrlConstants.URL_ID))
                .urlName(String.valueOf(UrlConstants.URL_NAME))
                .email(String.valueOf(UrlConstants.EMAIL))
                .userName(String.valueOf(UrlConstants.USERNAME))
                .password(String.valueOf(UrlConstants.PASSWORD))
                .phoneNumber(String.valueOf(UrlConstants.PHONE_NUMBER))
                .build();
    }

    private Url getInvalidMockUser() {
        return Url.builder()
                .urlId("")
                .urlName(null)
                .email(String.valueOf(UrlConstants.EMAIL))
                .userName(String.valueOf(UrlConstants.USERNAME))
                .password(String.valueOf(UrlConstants.PASSWORD))
                .phoneNumber(String.valueOf(UrlConstants.PHONE_NUMBER))
                .build();
    }

}

