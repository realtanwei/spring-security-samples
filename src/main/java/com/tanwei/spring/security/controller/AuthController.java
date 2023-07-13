package com.tanwei.spring.security.controller;

import com.tanwei.spring.security.entity.request.AuthenticationRequest;
import com.tanwei.spring.security.entity.response.AuthenticationResponse;
import com.tanwei.spring.security.service.AuthService;
import com.tanwei.spring.security.utils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @date 2023-07-07 14:30
 **/
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ApiResult<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ApiResult.success(authService.login(authenticationRequest));
    }
}
