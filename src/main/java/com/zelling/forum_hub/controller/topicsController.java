package com.zelling.forum_hub.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.zelling.forum_hub.entity.topic.Topic;
import com.zelling.forum_hub.entity.topic.TopicListDTO;
import com.zelling.forum_hub.entity.topic.TopicNewDTO;
import com.zelling.forum_hub.entity.topic.TopicRepository;
import com.zelling.forum_hub.entity.topic.TopicResponseDTO;
import com.zelling.forum_hub.service.topic.TopicService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/topics")
public class topicsController {
    @Autowired
    private TopicRepository repository;
    
    @Autowired
    private TopicService service;
    
    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> listAllTopics(Pageable pageable) {
        Page<TopicListDTO> topics = repository.findAll(pageable).map(TopicListDTO::new);
        return ResponseEntity.ok(topics);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid TopicNewDTO data, UriComponentsBuilder uriBuilder, @RequestHeader("Authorization") String token) {
        System.out.println("Controller, token = "+ token);
        var topic = service.createTopic(data, token);

        // var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }
    
    
}
