package com.tanwei.spring.security.service;

import com.tanwei.spring.security.entity.model.SysUser;
import com.tanwei.spring.security.entity.request.AuthenticationRequest;
import com.tanwei.spring.security.entity.response.AuthenticationResponse;
import com.tanwei.spring.security.exception.BusinessRuntimeException;
import com.tanwei.spring.security.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tanwei
 * @date 2023-07-07 14:36
 **/
@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private SysUserService userService;
    private JwtUtil jwtUtil;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        Optional<SysUser> sysUserOptional = userService.lambdaQuery().eq(SysUser::getUsername, username).oneOpt();
        if (sysUserOptional.isEmpty()) {
            log.error("用户【{}】不存在；", username);
            throw new BusinessRuntimeException("用户不存在");
        }

        SysUser sysUser = sysUserOptional.get();

        String realPassword = sysUser.getPassword();
        if (!passwordEncoder.matches(password, realPassword)) {
            log.error("用户【{}】密码错误；", username);
            throw new BusinessRuntimeException("用户密码错误");
        }
        // 生成jwt
        String token = jwtUtil.generateToken(sysUser);
        return AuthenticationResponse.builder().token(token).build();
    }
}
