package com.example.securityservice.config;

import com.example.securityservice.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class ApplicationSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {


        return new UserDetailsServiceImpl();

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                .requestMatchers("/swagger-ui/**",
                                        "/swagger-ui.html/**", "/v3/api-docs/**",
                                        "/security/welcome",
                                        "/security/addUser",
                                        "/jwt/authenticate")
                                .permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .formLogin().loginPage("/login").permitAll()
                                .and()
                                .httpBasic(Customizer.withDefaults());
                    } catch (Exception e) {
                        log.error("Exception while validating the security:", e);
                    }
                });
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
