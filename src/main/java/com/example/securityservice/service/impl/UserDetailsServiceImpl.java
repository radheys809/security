package com.example.securityservice.service.impl;

import com.example.securityservice.dto.UserInfoUserDetails;
import com.example.securityservice.entity.UserInfo;
import com.example.securityservice.exception.UserNotFoundException;
import com.example.securityservice.repository.UserDetailsRepository;
import com.example.securityservice.service.UserInfoDetailsService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserInfoDetailsService {


    private  UserDetailsRepository userDetailsRepository;
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserInfo getUserById(String id) throws UserNotFoundException {
        return userDetailsRepository.
                findById(id).
                orElseThrow(UserNotFoundException::new);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = userDetailsRepository.findByUserName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public String addUserInfo(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder
                .encode(userInfo.getPassword()));
        userDetailsRepository.save(userInfo);
        return userInfo.getUserName()+", has been saved";
    }
}
