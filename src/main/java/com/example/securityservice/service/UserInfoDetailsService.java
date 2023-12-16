package com.example.securityservice.service;

import com.example.securityservice.entity.UserInfo;
import com.example.securityservice.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoDetailsService extends UserDetailsService {
   UserInfo getUserById(String id) throws UserNotFoundException;

   String addUserInfo(UserInfo userInfo);
}
