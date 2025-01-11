package com.zelling.forum_hub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zelling.forum_hub.entity.user.UserLoginDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/login")
public class loginController {
    @Autowired
    private AuthenticationManager manager;

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
    
}
