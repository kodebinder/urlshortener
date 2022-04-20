package com.company.resource;

import com.company.entity.User;
import com.company.service.UserMongoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pushkar.chauhan@wissen.com
 */
@CrossOrigin(origins = {"http://localhost:9031","http://localhost:8765"})
@RestController
@Slf4j
public class UserMongoResource {

    private final UserMongoServiceImpl userMongoServiceImpl;

    public UserMongoResource(
            UserMongoServiceImpl userMongoServiceImpl
    ) {
        this.userMongoServiceImpl = userMongoServiceImpl;
    }

    /**
     * @return users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userMongoServiceImpl.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}