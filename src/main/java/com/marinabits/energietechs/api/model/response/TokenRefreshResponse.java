package com.marinabits.energietechs.api.model.response;

public record TokenRefreshResponse(
        String accessToken,
        String refreshToken) {
}