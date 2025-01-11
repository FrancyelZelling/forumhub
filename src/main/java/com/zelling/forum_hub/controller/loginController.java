package com.zelling.forum_hub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zelling.forum_hub.entity.user.LoginResponseDTO;
import com.zelling.forum_hub.entity.user.User;
import com.zelling.forum_hub.entity.user.UserLoginDTO;
import com.zelling.forum_hub.entity.user.UserRegisterDTO;
import com.zelling.forum_hub.entity.user.UserRepository;
import com.zelling.forum_hub.infra.security.TokenService;

import jakarta.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/auth")
public class loginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = manager.authenticate(token);

        var newToken = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(newToken));
    }
    
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User user = new User(data.username(), data.email(), encryptedPassword);

        this.repository.save(user);

        return ResponseEntity.ok().build();
    }
    
}
