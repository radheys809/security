package com.example.securityservice.controller;

import com.example.securityservice.dto.JwtAuthRequest;
import com.example.securityservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    JwtService jwtService;
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody JwtAuthRequest jwtAuthRequest){

return jwtService.generateToken(jwtAuthRequest.userName());
    }
}
