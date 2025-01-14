package com.zelling.forum_hub.entity.course;

import java.util.List;
import java.util.stream.Collectors;

import com.zelling.forum_hub.entity.topic.TopicSimpleDTO;

public record CourseListDTO(
    String name,
    String category,
    List<TopicSimpleDTO> topics
) {
//    public CourseListDTO(Course course){
//     this.name = course.getName();
//     this.category = course.getCategory().name();
//     this.topics = course.getTopics().stream().map(topic -> new TopicSimpleDTO(topic.getTitle(), topic.getMessage(), topic.getCreatedAt(), topic.getAuthor().getName())).toList();
//    }
    public CourseListDTO(Course course){
        this(
            course.getName(),
            course.getCategory().name(),
            course.getTopics().stream().map(topics -> new TopicSimpleDTO(topics)).toList()
        );
    }
}
