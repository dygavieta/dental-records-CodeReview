package com.example.dental_db.dentalrecords.service;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);
}
