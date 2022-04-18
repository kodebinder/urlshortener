package com.company.repository;

import com.company.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author pushkar.chauhan@wissen.com
 */
public interface UserMongoRepository extends MongoRepository<User, Long> {
}