package com.example.securityservice.controller;

import com.example.securityservice.entity.UserInfo;
import com.example.securityservice.exception.UserNotFoundException;
import com.example.securityservice.service.UserInfoDetailsService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/security")
@CrossOrigin(origins = "http://localhost:7001")
public class TestSecurityAccessController {

    @Autowired
    private UserInfoDetailsService userInfoDetailsService;

    @GetMapping({"/welcome", ""})
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserInfo getUserById(@PathVariable("userId") String userId) throws UserNotFoundException {
        return userInfoDetailsService.getUserById(userId);
    }

    @GetMapping("/access")
    @PreAuthorize("hasAuthority('ROLE_VIEWER')")
    public Object hasAccess() {
        return "Accessed";
    }

    @PostMapping("/addUser")

    public Object addNewUser(@RequestBody UserInfo userInfo) {
        String message = userInfoDetailsService.addUserInfo(userInfo);
        return ResponseEntity.ok()
                .body(Map.of("message", message));

    }
}
