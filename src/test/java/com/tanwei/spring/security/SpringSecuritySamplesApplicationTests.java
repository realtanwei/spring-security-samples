package com.tanwei.spring.security;

import com.tanwei.spring.security.entity.model.SysUser;
import com.tanwei.spring.security.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
class SpringSecuritySamplesApplicationTests {

    @Autowired
    SysUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {

    }

    @Test
    void register() {

    }

}
