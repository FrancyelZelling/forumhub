package com.zelling.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.zelling.forum_hub.entity.answer.AnswerCreateDTO;
import com.zelling.forum_hub.entity.answer.AnswerDetailDTO;
import com.zelling.forum_hub.service.answer.AnswerService;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class answerController {
    @Autowired
    private AnswerService service;
    
    @PostMapping("/topics/{id}")
    public ResponseEntity addAnswer(@PathVariable Long id, @RequestBody @Valid AnswerCreateDTO data, @RequestHeader("Authorization") String token) {
        var answer = service.addNewAnswer(id, data, token);
        return ResponseEntity.ok(new AnswerDetailDTO(answer));
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity getAnswer(@PathVariable Long id) {
        var answer = service.getAnswer(id);
        return ResponseEntity.ok(new AnswerDetailDTO(answer));
    }

    @PutMapping("/answers/{id}")
    public ResponseEntity getAnswer(@PathVariable Long id, @RequestBody @Valid AnswerCreateDTO data, @RequestHeader("Authorization") String token) {
        var answer = service.updateAnswer(id, data, token);
        return ResponseEntity.ok(new AnswerDetailDTO(answer));
    }
    
    @DeleteMapping("/answers/{id}")
    public ResponseEntity deleteAnswer(@PathVariable Long id, @RequestHeader("Authorization") String token ) {
        service.deleteAnswer(id, token);
        return ResponseEntity.ok().build();
    }
}
