package com.example.travelshooting.domain.user.dto;

import com.example.travelshooting.global.common.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtAuthResDto implements BaseDtoDataType {

    // access token 인증 방식.
    private final String tokenAuthScheme;

    // access token.
    private final String accessToken;

    // refresh token.
    private final String refreshToken;
}
