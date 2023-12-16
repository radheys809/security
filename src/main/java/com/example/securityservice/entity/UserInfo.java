package com.example.securityservice.entity;

import jakarta.ws.rs.GET;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "user_details")
@Getter
@Setter
public class UserInfo {

    @Id
    private String userId;
    private String userName;
    private String roles;
    private String password;

}
