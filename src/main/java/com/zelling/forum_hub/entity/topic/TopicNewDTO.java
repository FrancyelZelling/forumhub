package com.zelling.forum_hub.entity.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicNewDTO(
    @NotBlank
    String title,
    @NotBlank
    String message
) {
}