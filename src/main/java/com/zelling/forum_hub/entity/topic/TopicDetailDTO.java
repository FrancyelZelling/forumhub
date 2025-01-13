package com.zelling.forum_hub.entity.topic;

import java.util.List;
import java.util.stream.Collectors;

import com.zelling.forum_hub.entity.answer.Answer;
import com.zelling.forum_hub.entity.answer.AnswerDetailDTO;

public record TopicDetailDTO (
        Long id,
        String title,
        String message,
        TopicStatus status,
        String author,
        List<AnswerDetailDTO> answers
){
    public TopicDetailDTO (Topic topic){
        this(topic.getId(),topic.getTitle(), topic.getMessage(), topic.getStatus(),topic.getAuthor().getName(),
        topic.getAnswers().stream().map(AnswerDetailDTO::new).collect(Collectors.toList())
        );
    }
}
