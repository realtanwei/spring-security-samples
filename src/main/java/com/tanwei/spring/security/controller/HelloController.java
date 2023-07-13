package com.tanwei.spring.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @date 2023-07-04 14:44
 **/
@RestController
@RequestMapping("/api/v1/")
public class HelloController {


    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello() {
        String message = "Hello World!";
        return ResponseEntity.ok(message);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() {
        String message = "Hello test!";
        return ResponseEntity.ok(message);
    }

    @GetMapping(value = "/wa")
    public ResponseEntity<String> wa() {
        String message = "Hello wa!";
        return ResponseEntity.ok(message);
    }
}
