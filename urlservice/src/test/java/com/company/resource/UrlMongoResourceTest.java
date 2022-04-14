package com.company.resource;

import com.company.constant.UrlConstants;
import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import com.company.service.UrlMongoService;
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

@ContextConfiguration(classes = {UrlMongoResource.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UrlMongoResource.class)
class UrlMongoResourceTest {

    @MockBean
    private UrlMongoService urlMongoService;

    @MockBean
    private UrlValidatorImpl urlValidatorImpl;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateShortUrl() throws Exception {
        Mockito.when(urlValidatorImpl.validatePostRequestInputParameters(Mockito.any(UrlDto.class))).thenReturn(Boolean.TRUE);
        Mockito.when(urlMongoService.createShortUrl(Mockito.any(UrlDto.class))).thenReturn(getValidMockUserDto());
        mockMvc.perform(MockMvcRequestBuilders.post(String.valueOf(UrlConstants.URL_MONGO_POST_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getValidMockUserDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.status().is(201));
        Mockito.verify(urlValidatorImpl, Mockito.times(1)).validatePostRequestInputParameters(Mockito.any(UrlDto.class));
        Mockito.verify(urlMongoService, Mockito.times(1)).createShortUrl(Mockito.any(UrlDto.class));
    }

    @Test
    void testCreateShortUrl_whenValidationFails() throws Exception {
        Mockito.when(urlValidatorImpl.validatePostRequestInputParameters(Mockito.any(UrlDto.class))).thenReturn(Boolean.FALSE);
        Mockito.when(urlMongoService.createShortUrl(Mockito.any(UrlDto.class))).thenReturn(getValidMockUserDto());
        mockMvc.perform(MockMvcRequestBuilders.post(String.valueOf(UrlConstants.URL_MONGO_POST_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getValidMockUserDto())))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof UrlRequestException));
        Mockito.verify(urlValidatorImpl, Mockito.times(1)).validatePostRequestInputParameters(Mockito.any(UrlDto.class));
        Mockito.verify(urlMongoService, Mockito.times(0)).createShortUrl(Mockito.any(UrlDto.class));
    }

    @Test
    void testGetLongUrl() throws Exception {
        Mockito.when(urlValidatorImpl.validateGetRequestInputParameters(Mockito.any(UrlDto.class))).thenReturn(Boolean.TRUE);
        Mockito.when(urlMongoService.getLongUrl(Mockito.any(String.class))).thenReturn(getValidMockUserDto());
        mockMvc.perform(MockMvcRequestBuilders.get(UrlConstants.URL_MONGO_GET_URL.toString(), UrlConstants.URL_ID.toString()))
                .andExpect(MockMvcResultMatchers.status().isFound());
        Mockito.verify(urlValidatorImpl,Mockito.times(1)).validateGetRequestInputParameters(Mockito.any(UrlDto.class));
        Mockito.verify(urlMongoService,Mockito.times(1)).getLongUrl((Mockito.any(String.class)));
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
                .urlName(String.valueOf(UrlConstants.URL_NAME))
                .email(String.valueOf(UrlConstants.EMAIL))
                .userName(String.valueOf(UrlConstants.USERNAME))
                .build();
    }

}

