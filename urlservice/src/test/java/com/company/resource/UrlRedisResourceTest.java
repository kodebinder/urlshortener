package com.company.resource;

import com.company.constant.UrlConstants;
import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import com.company.service.UrlRedisService;
import com.company.validator.UrlValidatorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ContextConfiguration(classes = {UrlRedisResource.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UrlRedisResource.class)
class UrlRedisResourceTest {

    @MockBean
    private UrlValidatorImpl urlValidatorImpl;

    @MockBean
    private UrlRedisService urlRedisService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateShortUrl() throws Exception {
        Mockito.when(urlValidatorImpl.validatePostRequestInputParameters(Mockito.any(UrlDto.class))).thenReturn(Boolean.TRUE);
        Mockito.when(urlRedisService.createShortUrl(Mockito.any(UrlDto.class))).thenReturn(getValidMockUserDto());
        mockMvc.perform(MockMvcRequestBuilders.post(String.valueOf(UrlConstants.URL_REDIS_POST_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getValidMockUserDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.status().is(201));
        Mockito.verify(urlValidatorImpl, Mockito.times(1)).validatePostRequestInputParameters(Mockito.any(UrlDto.class));
        Mockito.verify(urlRedisService, Mockito.times(1)).createShortUrl(Mockito.any(UrlDto.class));
    }

    @Test
    void testCreateShortUrl_whenValidationFails() throws Exception {
        Mockito.when(urlValidatorImpl.validatePostRequestInputParameters(Mockito.any(UrlDto.class))).thenReturn(Boolean.FALSE);
        Mockito.when(urlRedisService.createShortUrl(Mockito.any(UrlDto.class))).thenReturn(getValidMockUserDto());
        mockMvc.perform(MockMvcRequestBuilders.post(String.valueOf(UrlConstants.URL_REDIS_POST_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getValidMockUserDto())))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof UrlRequestException));
        Mockito.verify(urlValidatorImpl, Mockito.times(1)).validatePostRequestInputParameters(Mockito.any(UrlDto.class));
        Mockito.verify(urlRedisService, Mockito.times(0)).createShortUrl(Mockito.any(UrlDto.class));
    }

    @Test
    void testGetLongUrl() throws Exception {
        Mockito.when(urlValidatorImpl.validateGetRequestInputParameters(Mockito.any(UrlDto.class))).thenReturn(Boolean.TRUE);
        Mockito.when(urlRedisService.getLongUrl(Mockito.any(String.class))).thenReturn(getValidMockUserDto());
        mockMvc.perform(MockMvcRequestBuilders.get(String.valueOf(UrlConstants.URL_REDIS_GET_URL), String.valueOf(UrlConstants.URL_ID)))
                .andExpect(MockMvcResultMatchers.status().isFound());
        Mockito.verify(urlValidatorImpl, Mockito.times(1)).validateGetRequestInputParameters(Mockito.any(UrlDto.class));
        Mockito.verify(urlRedisService, Mockito.times(1)).getLongUrl((Mockito.any(String.class)));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

}

