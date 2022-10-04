package com.example.dental_db.dentalrecords.controller;

import com.example.dental_db.dentalrecords.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping(value = "/token")
    public String token(Authentication authentication){
        return tokenService.generateToken(authentication);
    }


}
