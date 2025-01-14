package com.zelling.forum_hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zelling.forum_hub.entity.course.CourseListDTO;
import com.zelling.forum_hub.entity.course.CourseNewDTO;
import com.zelling.forum_hub.entity.course.CourseRepository;
import com.zelling.forum_hub.service.course.CourseService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/courses")
public class courseController {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<Page<CourseListDTO>> getCourses(Pageable pageable){
        Page<CourseListDTO> courses = repository.findAll(pageable).map(CourseListDTO::new);
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody @Valid CourseNewDTO data){
        var course = service.createCourse(data);
        
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity createCourse(@RequestBody @Valid CourseNewDTO data, @PathVariable Long id){
        var course = service.updateCourse(data, id);
        
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity createCourse(@PathVariable Long id){
        service.deleteCourse(id);
        
        return ResponseEntity.ok("course deleted");
    }
}
