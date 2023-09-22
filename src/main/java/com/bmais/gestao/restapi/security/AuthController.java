package com.bmais.gestao.restapi.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshToken() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
