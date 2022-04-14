package com.company.validator;

import com.company.dto.UrlDto;
import com.company.exception.UrlRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Component
@Slf4j
public class UrlValidatorImpl {

    public UrlValidatorImpl() {
    }

    /**
     * Validates GET Request Input Parameters
     *
     * @return boolean
     */
    public boolean validateGetRequestInputParameters(UrlDto urlDto) {
        UrlValidator.ValidationResult validationResult = UrlValidator.isUrlIdValid().apply(urlDto);
        if (validationResult != UrlValidator.ValidationResult.SUCCESS) {
            throw new UrlRequestException("Bad Request Exception is thrown : " + validationResult.name());
        }

        return true;
    }

    /**
     * Validates POST Request Input Parameters
     *
     * @return boolean
     */
    public boolean validatePostRequestInputParameters(UrlDto urlDto) {
        UrlValidator.ValidationResult validationResult = UrlValidator.isUrlNameValid()
                .and(UrlValidator.isEmailValid())
                .and(UrlValidator.isUsernameValid())
                .and(UrlValidator.isPasswordValid())
                .and(UrlValidator.isPhoneNumberValid())
                .apply(urlDto);

        if (validationResult != UrlValidator.ValidationResult.SUCCESS) {
            throw new IllegalStateException(validationResult.name());
        }

        return true;
    }


}
