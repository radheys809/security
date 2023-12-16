package com.example.securityservice.repository;

import com.example.securityservice.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDetailsRepository extends MongoRepository<UserInfo, String> {

    Optional<UserInfo> findByUserName(String username);
}

