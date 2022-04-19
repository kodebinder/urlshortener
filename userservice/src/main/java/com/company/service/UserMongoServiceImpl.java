package com.company.service;

import com.company.dto.UrlDto;
import com.company.entity.User;
import com.company.helper.UserHelper;
import com.company.repository.UserMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author pushkar.chauhan@wissen.com
 */
@Service
@Slf4j
public class UserMongoServiceImpl implements UserMongoService {

    private final UserMongoRepository userMongoRepository;

    public UserMongoServiceImpl(
            @Qualifier("userMongoRepository") UserMongoRepository userMongoRepository
    ) {
        this.userMongoRepository = userMongoRepository;
    }

    /**
     * @param urlDto urlDto passed by user in HTTP Request Body
     */
    @Transactional
    public void saveUser(UrlDto urlDto) {
        User user = UserHelper.mapUrlDtoToUserEntity(urlDto);
        Optional<User> optionalUser = userMongoRepository.findByUserName(user.getUserName());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            log.info("Fetched User : {}", user);
        } else {
            user.setId(new BigInteger(String.valueOf(userMongoRepository.findAll().size() + 1)));
            user.setEmail(urlDto.getEmail());
            user.setUserName(urlDto.getUserName());
            user.setPassword(urlDto.getPassword());
            user.setPhoneNumber(urlDto.getPhoneNumber());
        }
        userMongoRepository.save(user);
        log.info("Persisted user : {}", user);
    }

}