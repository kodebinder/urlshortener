package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;

/**
 * @author pushkar.chauhan@wissen.com
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UrlDto {
    private BigInteger id;
    private String urlId;

    @NotEmpty
    @Size(min=4,message = "url must be minimum 4 characters")
    private String urlName;

    @NotEmpty
    private String shortUrlName;

    @Email(message = "invalid email address passed")
    private String email;

    @NotEmpty
    private String userName;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password length must be between 3 and 10 characters")
    private String password;

    @NotEmpty
    private String phoneNumber;
}
