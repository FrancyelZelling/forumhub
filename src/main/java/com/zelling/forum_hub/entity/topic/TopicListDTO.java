package com.zelling.forum_hub.entity.topic;

public record TopicListDTO(
    String title,
    String message,
    TopicStatus status
) {
   public TopicListDTO(Topic topic){
    this(topic.getTitle(), topic.getMessage(), topic.getStatus());
   }
}
