package com.zelling.forum_hub.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/topics")
public class topicsController {
    
    @GetMapping
    public String getMethodName() {
        return new String("Hello world");
    }
    
}
