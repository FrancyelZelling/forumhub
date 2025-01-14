package com.zelling.forum_hub.service.topic;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelling.forum_hub.entity.answer.AnswerRepository;
import com.zelling.forum_hub.entity.topic.Topic;
import com.zelling.forum_hub.entity.topic.TopicNewDTO;
import com.zelling.forum_hub.entity.topic.TopicRepository;
import com.zelling.forum_hub.entity.topic.TopicStatus;
import com.zelling.forum_hub.entity.topic.TopicUpdateDTO;
import com.zelling.forum_hub.service.course.CourseService;
import com.zelling.forum_hub.service.user.UserService;

import jakarta.transaction.Transactional;

@Service
public class TopicService {

  @Autowired
  private TopicRepository topicRepository;
  
  @Autowired
  private UserService userService;

  @Autowired 
  AnswerRepository answerRepository;

  @Autowired
  private CourseService courseService;

  @Transactional
  public Topic createTopic(TopicNewDTO data, String token){
    var user = userService.findUser(token);
    Topic topic = new Topic(data,user);
    topic.setStatus(TopicStatus.NOT_ANSWERED);

    System.out.println("------------- author " + topic.getAuthor());
    var newTopic = topicRepository.save(topic);
    return newTopic;
  }

  @Transactional
  public Topic updateTopic(Long id, TopicUpdateDTO data, String token) {
    var topic = topicRepository.findById(id);
    if(topic.isEmpty()) throw new RuntimeException("cannot find topic to update");
    var newTopic = topic.get();
    var user = userService.findUser(token);

    if (topic.get().getAuthor().getUsername() != user.getUsername()) throw new RuntimeException("only the author can update the topic");

    if(data.title() != null) newTopic.setTitle(data.title());
    if(data.message() != null) newTopic.setMessage(data.message());
    if(data.status() != null) newTopic.setStatus(data.status());
    if(data.courseId() != null) newTopic.setCourse(courseService.getCourse(data.courseId()));

    return topicRepository.save(newTopic);
  }

  public void deleteTopic(Long id, String token) {
    var topic = topicRepository.findById(id);
    if(topic.isEmpty()) throw new RuntimeException("topic not found");

    var user = userService.findUser(token);
    if (topic.get().getAuthor().getUsername() != user.getUsername()) throw new RuntimeException("only the author can delete the topic");

    topicRepository.delete(topic.get());
  }

}
