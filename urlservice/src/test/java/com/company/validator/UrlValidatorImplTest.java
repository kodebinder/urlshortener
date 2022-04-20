package com.company.validator;

import com.company.constant.UrlConstants;
import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {UrlValidatorImpl.class})
@ExtendWith(MockitoExtension.class)
class UrlValidatorImplTest {

    private static UrlValidatorImpl urlValidatorImpl;

    @BeforeAll
    static void beforeAll() {
        urlValidatorImpl = new UrlValidatorImpl();
    }

    @Test
    void testValidateGetRequestInputParameters() {
        urlValidatorImpl.validateGetRequestInputParameters(getValidMockUserDto());
    }

    @Test
    void testValidateGetRequestInputParameters2() {
        assertThrows(UrlRequestException.class,
                () -> urlValidatorImpl.validateGetRequestInputParameters(new UrlDto(new BigInteger("123"),
                        "https://example.org/example", "https://example.org/example", "https://example.org/example", "jane",
                        "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    }

    @Test
    void testValidateGetRequestInputParameters3() {
        assertThrows(UrlRequestException.class,
                () -> urlValidatorImpl.validateGetRequestInputParameters(
                        new UrlDto(new BigInteger("123"), "42", "https://example.org/example", "https://example.org/example", "jane",
                                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
    }


    @Test
    void testValidatePostRequestInputParameters() {
        assertThrows(IllegalStateException.class,
                () -> urlValidatorImpl.validatePostRequestInputParameters(new UrlDto(new BigInteger("123"),
                        "https://example.org/example", "https://example.org/example", "https://example.org/example", "jane",
                        "https://example.org/example", "https://example.org/example", "https://example.org/example")));
        assertThrows(IllegalStateException.class,
                () -> urlValidatorImpl.validatePostRequestInputParameters(
                        new UrlDto(new BigInteger("123"), "https://example.org/example", "UUU", "https://example.org/example", "jane",
                                "https://example.org/example", "https://example.org/example", "https://example.org/example")));
        assertThrows(IllegalStateException.class,
                () -> urlValidatorImpl.validatePostRequestInputParameters(new UrlDto(new BigInteger("123"),
                        "https://example.org/example", "", "https://example.org/example", "jane",
                        "https://example.org/example", "https://example.org/example", "https://example.org/example")));
        assertThrows(IllegalStateException.class,
                () -> urlValidatorImpl.validatePostRequestInputParameters(
                        new UrlDto(new BigInteger("123"), "https://example.org/example", "https://example.org/example",
                                "jane.doe@example.org", "jane", "https://example.org/example", "https://example.org/example", "https://example.org/example")));
        assertThrows(IllegalStateException.class,
                () -> urlValidatorImpl.validatePostRequestInputParameters(new UrlDto(new BigInteger("123"),
                        "https://example.org/example", "https://example.org/example", "jane.doe@example.org",
                        "https://example.org/example", "https://example.org/example", "https://example.org/example", "https://example.org/example")));
        assertThrows(IllegalStateException.class,
                () -> urlValidatorImpl.validatePostRequestInputParameters(new UrlDto(new BigInteger("123"),
                        "https://example.org/example", "https://example.org/example", "jane.doe@example.org", "jane",
                        "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", "https://example.org/example", "https://example.org/example")));

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
