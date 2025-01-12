package com.zelling.forum_hub.entity.topic;

public record TopicResponseDTO(
    String title,
    String message,
    TopicStatus status
) {
   public TopicResponseDTO(Topic topic){
    this(topic.getTitle(), topic.getMessage(), topic.getStatus());
   } 
}
