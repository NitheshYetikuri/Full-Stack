package com.furniturestore.service;

import com.furniturestore.entity.JwtRequest;
import com.furniturestore.entity.JwtResponse;

public interface JwtService {
    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;
}
