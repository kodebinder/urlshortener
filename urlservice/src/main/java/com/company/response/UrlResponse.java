package com.company.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author pushkar.chauhan@wissen.com
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UrlResponse {
    private BigInteger id;
    private String urlId;
    private String urlName;
    private String userName;
}
