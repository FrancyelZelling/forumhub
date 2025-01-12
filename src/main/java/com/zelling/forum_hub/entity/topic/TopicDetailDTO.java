package com.zelling.forum_hub.entity.topic;

public record TopicDetailDTO (
        String title,
        String message,
        TopicStatus status,
        String author
){
    public TopicDetailDTO (Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getStatus(),topic.getAuthor().getName());
    }
}
