package com.zelling.forum_hub.entity.user;

public record UserRegisterDTO(
    String username,
    String email,
    String password
) {
    
}
