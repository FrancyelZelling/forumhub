package com.zelling.forum_hub.controller;

import com.zelling.forum_hub.entity.topic.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.zelling.forum_hub.service.topic.TopicService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



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

    @GetMapping("/{id}")
    public ResponseEntity listOneTopic(@PathVariable Long id){
        var topic = repository.findById(id);
        return ResponseEntity.ok(new TopicDetailDTO(topic.get()));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid TopicNewDTO data, UriComponentsBuilder uriBuilder, @RequestHeader("Authorization") String token) {
        System.out.println("Controller, token = "+ token);
        var topic = service.createTopic(data, token);

        // var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO data, @RequestHeader("Authorization") String token) {
        var updateTopic = service.updateTopic(id, data, token);
    
        return ResponseEntity.ok(new TopicDetailDTO(updateTopic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        service.deleteTopic(id, token);
    
        return ResponseEntity.ok().build();
    }
    
}
