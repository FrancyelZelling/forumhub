package com.zelling.forum_hub.controller;

import org.springframework.web.bind.annotation.RestController;

import com.zelling.forum_hub.entity.topic.Topic;
import com.zelling.forum_hub.entity.topic.TopicListDTO;
import com.zelling.forum_hub.entity.topic.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/topics")
public class topicsController {
    @Autowired
    private TopicRepository repository;
    
    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> listAllTopics(Pageable pageable) {
        Page<TopicListDTO> topics = repository.findAll(pageable).map(TopicListDTO::new);
        return ResponseEntity.ok(topics);
    }
    
}
