package com.zelling.forum_hub.service.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelling.forum_hub.entity.topic.Topic;
import com.zelling.forum_hub.entity.topic.TopicNewDTO;
import com.zelling.forum_hub.entity.topic.TopicRepository;
import com.zelling.forum_hub.entity.topic.TopicStatus;
import com.zelling.forum_hub.entity.user.UserRepository;
import com.zelling.forum_hub.infra.security.TokenService;

import jakarta.transaction.Transactional;

@Service
public class TopicService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private TokenService tokenService;

   @Transactional
   public Topic createTopic(TopicNewDTO data, String token){
       var email = tokenService.validateToken(token.replace("Bearer ", ""));
       System.out.println("email=" + token);
         var user = userRepository.findUser(email);
        if (user.isEmpty()) {
          throw new RuntimeException("user not found");
        }

        Topic topic = new Topic(data,user.get());
          topic.setStatus(TopicStatus.NOT_ANSWERED);

       System.out.println("------------- author " + topic.getAuthor());
        var newTopic = topicRepository.save(topic);
        return newTopic;
   }
}
