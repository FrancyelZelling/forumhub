package com.zelling.forum_hub.service.topic;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelling.forum_hub.entity.answer.Answer;
import com.zelling.forum_hub.entity.answer.AnswerCreateDTO;
import com.zelling.forum_hub.entity.answer.AnswerRepository;
import com.zelling.forum_hub.entity.topic.Topic;
import com.zelling.forum_hub.entity.topic.TopicNewDTO;
import com.zelling.forum_hub.entity.topic.TopicRepository;
import com.zelling.forum_hub.entity.topic.TopicStatus;
import com.zelling.forum_hub.entity.topic.TopicUpdateDTO;
import com.zelling.forum_hub.entity.user.User;
import com.zelling.forum_hub.entity.user.UserRepository;
import com.zelling.forum_hub.infra.security.TokenService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class TopicService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TopicRepository topicRepository;
  
  @Autowired
  private TokenService tokenService;

  @Autowired 
  AnswerRepository answerRepository;

  @Transactional
  public Topic createTopic(TopicNewDTO data, String token){
    var user = findUser(token);
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
    var user = findUser(token);

    if (topic.get().getAuthor().getUsername() != user.getUsername()) throw new RuntimeException("only the author can update the topic");

    if(data.title() != null) newTopic.setTitle(data.title());
    if(data.message() != null) newTopic.setMessage(data.message());
    if(data.status() != null) newTopic.setStatus(data.status());

    return topicRepository.save(newTopic);
  }

  public void deleteTopic(Long id, String token) {
    var topic = topicRepository.findById(id);
    if(topic.isEmpty()) throw new RuntimeException("topic not found");

    var user = findUser(token);
    if (topic.get().getAuthor().getUsername() != user.getUsername()) throw new RuntimeException("only the author can delete the topic");

    topicRepository.delete(topic.get());
  }

  private User findUser(String token){
    var email = tokenService.validateToken(token.replace("Bearer ", ""));
    System.out.println("email=" + token);
    var user = userRepository.findUser(email);
    if (user.isEmpty()) {
      throw new RuntimeException("user not found");
    }
    return user.get();
  }

@Transactional
public Answer addNewAnswer(Long TopicID, AnswerCreateDTO data, String token) {
    var topic = topicRepository.findById(TopicID);
    if (topic.isEmpty()) throw new RuntimeException("topic not found");

    var user = findUser(token);
    Answer answer = new Answer(data.message(), LocalDateTime.now(), topic.get(), user);

    answerRepository.save(answer);

    return answer;
}
}
