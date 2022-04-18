package com.company.helper;

import com.company.dto.UrlDto;
import com.company.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Component
@Slf4j
public class UserHelper {

    public UserHelper() {
    }

    /**
     * Mapping DTO to Entity
     *
     * @param urlDto UrlDto
     * @return User
     */
    public static User mapUrlDtoToUserEntity(UrlDto urlDto){
        User user = new User();
        user.setEmail(urlDto.getEmail());
        user.setUserName(urlDto.getUserName());
        user.setPassword(urlDto.getPassword());
        user.setPhoneNumber(urlDto.getPhoneNumber());

        log.info("Email : " + user.getEmail());
        log.info("UserName : " + user.getUserName());
        log.info("Password : " + user.getPassword());
        log.info("Phone Number : " + user.getPhoneNumber());

        return user;
    }


}