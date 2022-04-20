package com.company.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author pushkar.chauhan@wissen.com
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UrlResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigInteger id;
    private String urlId;
    private String urlName;
    private String shortUrlName;
    private String userName;
}
