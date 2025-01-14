package com.zelling.forum_hub.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelling.forum_hub.entity.user.User;
import com.zelling.forum_hub.entity.user.UserRepository;
import com.zelling.forum_hub.infra.security.TokenService;

import jakarta.transaction.Transactional;

@Service
public class UserService {
   @Autowired
   private TokenService tokenService;

   @Autowired
   private UserRepository userRepository;

   @Transactional
   public User findUser(String token){
    var email = tokenService.validateToken(token.replace("Bearer ", ""));
    System.out.println("email=" + token);
    var user = userRepository.findUser(email);
    if (user.isEmpty()) {
      throw new RuntimeException("user not found");
    }
    return user.get();
   }
}
