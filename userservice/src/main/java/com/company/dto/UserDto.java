package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pushkar.chauhan@wissen.com
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserDto {
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
}
