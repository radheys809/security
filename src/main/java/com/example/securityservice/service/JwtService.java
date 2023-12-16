package com.example.securityservice.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        createToken(claims, userName);
        return "";
    }

    private String createToken(Map<String,Object> claims, String userName) {
       return Jwts.builder()
               .setClaims(claims)
               .setSubject(userName)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
               .signWith(getSignKey(), SignatureAlgorithm.HS256)
               .compact();
    }

    private Key getSignKey() {
        byte [] keyBytes = Decoders.BASE64.decode("RAdheShyam");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
