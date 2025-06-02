package com.example.travelshooting.domain.user.dto;

import com.example.travelshooting.global.common.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenDto implements BaseDtoDataType {

    private final String AccessToken;

    private final String RefreshToken;
}
