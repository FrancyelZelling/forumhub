package com.zelling.forum_hub.entity.answer;

import java.time.LocalDateTime;

public record AnswerDetailDTO(
    String message,
    LocalDateTime createdAt,
    String author
) {
   public AnswerDetailDTO(Answer answer){
    this(answer.getMessage(), answer.getCreatedAt(), answer.getAuthor().getUsername());
   }
}
