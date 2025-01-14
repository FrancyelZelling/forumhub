package com.zelling.forum_hub.service.answer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zelling.forum_hub.entity.answer.Answer;
import com.zelling.forum_hub.entity.answer.AnswerCreateDTO;
import com.zelling.forum_hub.entity.answer.AnswerRepository;
import com.zelling.forum_hub.entity.topic.TopicRepository;
import com.zelling.forum_hub.service.user.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;
    
    @Transactional
    public Answer addNewAnswer(Long TopicID, AnswerCreateDTO data, String token) {
        var topic = topicRepository.findById(TopicID);
        if (topic.isEmpty()) throw new RuntimeException("topic not found");

        var user = userService.findUser(token);
        Answer answer = new Answer(data.message(), LocalDateTime.now(), topic.get(), user);

        answerRepository.save(answer);

        return answer;
    }

    public Answer getAnswer(Long id) {
        var answer = answerRepository.findById(id);
        if (answer.isEmpty()) throw new RuntimeException("answer not found");

        return answer.get();
    }

    @Transactional
    public Answer updateAnswer(Long id, AnswerCreateDTO data, String token) {
        var answer = this.getAnswer(id);
        var author = userService.findUser(token);

        if(!author.getUsername().equals(answer.getAuthor().getUsername())) throw new RuntimeException("only the author can update the answer");
        if(data.message() != null) answer.setMessage(data.message());
        
        answerRepository.save(answer);
        return answer;
    }

    public void deleteAnswer(Long id, String token) {
        var answer = this.getAnswer(id);
        var author = userService.findUser(token);

        if(!author.getUsername().equals(answer.getAuthor().getUsername())) throw new RuntimeException("only the author can delete the answer");

        answerRepository.delete(answer);
    }
}
