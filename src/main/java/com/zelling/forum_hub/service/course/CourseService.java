package com.zelling.forum_hub.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelling.forum_hub.entity.course.Course;
import com.zelling.forum_hub.entity.course.CourseNewDTO;
import com.zelling.forum_hub.entity.course.CourseRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CourseService {
   @Autowired
   private CourseRepository repository;
   
   public Course getCourse(Long id){
    var course = repository.findById(id);
    if(course.isEmpty()) throw new RuntimeException("course not found");

    return course.get();
   }

   @Transactional
   public Course createCourse(CourseNewDTO data) {
      Course course = new Course(data.name(), data.category());

      repository.save(course);
      return course;
   }

   @Transactional
   public Course updateCourse(CourseNewDTO data, Long id) {
      var course = repository.findById(id);
      if (course.isEmpty()) throw new RuntimeException("course not found");

      if (data.name() != null) course.get().setName(data.name());
      if (data.category() != null) course.get().setCategory(data.category());

      repository.save(course.get());

      return course.get();
   }

   public void deleteCourse(Long id) {
      var course = this.getCourse(id);
      repository.delete(course);
   }
}
