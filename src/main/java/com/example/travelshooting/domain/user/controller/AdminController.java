package com.example.travelshooting.domain.user.controller;

import com.example.travelshooting.domain.user.dto.UserReqDto;
import com.example.travelshooting.domain.user.dto.UserResDto;
import com.example.travelshooting.domain.user.service.AdminService;
import com.example.travelshooting.global.common.CommonResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 관리자 회원가입
    @PostMapping
    public ResponseEntity<CommonResDto<UserResDto>> adminSignup(
            @RequestPart(required = false) MultipartFile file,
            @Valid @RequestPart UserReqDto userReqDto
    ) {
        UserResDto adminSignup = adminService.adminSignup(userReqDto.getEmail(), userReqDto.getPassword(), userReqDto.getName(), file);

        return new ResponseEntity<>(new CommonResDto<>("관리자 회원가입 완료", adminSignup), HttpStatus.CREATED);
    }
}