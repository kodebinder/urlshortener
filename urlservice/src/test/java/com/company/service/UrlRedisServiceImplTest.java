package com.company.service;

import com.company.constant.UrlConstants;
import com.company.dto.UrlDto;
import com.company.entity.Url;
import com.company.exception.UrlRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UrlRedisServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class UrlRedisServiceImplTest {

    @Mock
    private StringRedisTemplate stringRedisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private UrlRedisServiceImpl urlRedisServiceImpl;

    @Test
    void test_createUrl() {
        Mockito.when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.doNothing().when(valueOperations).set(Mockito.anyString(), Mockito.anyString());
        UrlDto urlDto = urlRedisServiceImpl.createShortUrl(getValidMockUserDto());
        Assertions.assertThat(urlDto).isNotNull();
        Mockito.verify(stringRedisTemplate, Mockito.times(1)).opsForValue();
        Mockito.verify(valueOperations, Mockito.times(1)).set(Mockito.anyString(), Mockito.anyString());
    }


    @Test
    void testCreate_shouldThrowBadArgumentsExceptionWhenEmptyUrlIsPassed() {
        Assertions.assertThatThrownBy(() -> urlRedisServiceImpl.createShortUrl(getInvalidMockUserDto()))
                .isInstanceOf(UrlRequestException.class)
                .hasMessageContaining("Invalid urlName : " + getInvalidMockUser().getUrlName());
    }

    @Test
    void test_getUrl() {
        Mockito.when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(Mockito.anyString())).thenReturn(getValidMockUser().getUrlId());
        UrlDto urlDto = urlRedisServiceImpl.getLongUrl(getValidMockUser().getUrlId());
        Assertions.assertThat(urlDto).isNotNull();
        Assertions.assertThat(urlDto.getUrlId()).isNotNull();
        Assertions.assertThat(urlDto.getUrlName()).isNotNull();
        Mockito.verify(stringRedisTemplate, Mockito.atLeastOnce()).opsForValue();
        Mockito.verify(valueOperations, Mockito.atLeastOnce()).get(Mockito.anyString());
    }

    @Test
    void testGetUrl_shouldThrowBadArgumentsExceptionWhenEmptyUrlIdIsPassed() {
        Mockito.when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(Mockito.anyString())).thenReturn(null);
        Assertions.assertThatThrownBy(() -> urlRedisServiceImpl.getLongUrl(""))
                .isInstanceOf(UrlRequestException.class)
                .hasMessageContaining("There is no shorter URL for urlId : " + getInvalidMockUser().getUrlId());
    }

    @Test
    void testGetUrl_shouldThrowBadArgumentsExceptionWhenNullUrlIdIsPassed() {
        Mockito.when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(Mockito.anyString())).thenReturn(null);
        Assertions.assertThatThrownBy(() -> urlRedisServiceImpl.getLongUrl(getValidMockUser().getUrlId()))
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

