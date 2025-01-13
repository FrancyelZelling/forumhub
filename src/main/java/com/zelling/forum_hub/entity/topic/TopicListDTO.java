package com.zelling.forum_hub.entity.topic;

public record TopicListDTO(
    Long id,
    String title,
    String message,
    TopicStatus status
) {
   public TopicListDTO(Topic topic){
    this(topic.getId(),topic.getTitle(), topic.getMessage(), topic.getStatus());
   }
}
