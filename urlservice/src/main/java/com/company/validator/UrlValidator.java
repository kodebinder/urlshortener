package com.company.validator;

import com.company.dto.UrlDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author pushkar.chauhan@wissen.com
 */
public interface UrlValidator extends Function<UrlDto, UrlValidator.ValidationResult> {

    enum ValidationResult {
        SUCCESS,
        URL_ID_NOT_VALID,
        URL_NOT_VALID,
        EMAIL_NOT_VALID,
        USERNAME_NOT_VALID,
        PASSWORD_NOT_VALID,
        PHONE_NUMBER_NOT_VALID
    }

    static UrlValidator isUrlIdValid() {
        return urlDto -> StringUtils.isAlphanumeric(urlDto.getUrlId())
                && StringUtils.length(urlDto.getUrlId()) == 8
                ? ValidationResult.SUCCESS : ValidationResult.URL_ID_NOT_VALID;
    }

    static UrlValidator isUrlNameValid() {
        return urlDto -> org.apache.commons.validator.routines.UrlValidator.getInstance().isValid(urlDto.getUrlName())
                ? ValidationResult.SUCCESS : ValidationResult.URL_NOT_VALID;
    }

    static UrlValidator isEmailValid() {
        return urlDto -> EmailValidator.getInstance().isValid(urlDto.getEmail())
                ? ValidationResult.SUCCESS : ValidationResult.EMAIL_NOT_VALID;
    }

    static UrlValidator isUsernameValid() {
        return urlDto -> !Objects.isNull(urlDto.getUserName())
                && urlDto.getUserName().chars().allMatch(Character::isLetterOrDigit)
                ? ValidationResult.SUCCESS : ValidationResult.USERNAME_NOT_VALID;
    }

    static UrlValidator isPasswordValid() {
        return urlDto ->
                Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")
                        .matcher(urlDto.getPassword())
                        .matches()
                        ? ValidationResult.SUCCESS : ValidationResult.PASSWORD_NOT_VALID;
    }

    static UrlValidator isPhoneNumberValid() {
        return urlDto ->
                Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")
                        .matcher(urlDto.getPhoneNumber())
                        .matches()
                        ? ValidationResult.SUCCESS : ValidationResult.PHONE_NUMBER_NOT_VALID;
    }

    default UrlValidator and(UrlValidator other) {
        return urlDto -> {
            ValidationResult validationResult = this.apply(urlDto);
            return validationResult.equals(ValidationResult.SUCCESS) ? other.apply(urlDto) : validationResult;
        };
    }

}
