package com.spring.boot.mysql.dto;

public record LoginResponse(String token, long expiresIn) {
}
