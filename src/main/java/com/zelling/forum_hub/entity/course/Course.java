package com.zelling.forum_hub.entity.course;

import java.util.ArrayList;
import java.util.List;

import com.zelling.forum_hub.entity.topic.Topic;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   
   @Enumerated(EnumType.STRING)
   private CourseCategory category;

   @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<Topic> topics = new ArrayList<>();

public Course() {
}

public Course(String name, CourseCategory category) {
    this.name = name;
    this.category = category;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public CourseCategory getCategory() {
    return category;
}

public void setCategory(CourseCategory category) {
    this.category = category;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public List<Topic> getTopics() {
    return this.topics;
}

public void setTopics(List<Topic> topics) {
    this.topics = topics;
}

}
