package com.company.repository;

import com.company.entity.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author pushkar.chauhan@wissen.com
 */
public interface UrlMongoRepository extends MongoRepository<Url, Long> {
    Optional<Url> findByUrlId(String urlId);
}