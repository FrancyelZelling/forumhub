package com.zelling.forum_hub.entity.topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zelling.forum_hub.entity.answer.Answer;
import com.zelling.forum_hub.entity.user.User;

import jakarta.persistence.*;

@Entity(name = "Topic")
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "topic_message")
    private String message;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;
    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    Topic(){}

    public Topic(String title, String message, LocalDateTime createdAt, User author, TopicStatus status) {
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.author = author;
        this.status = status;
    }

    public Topic(TopicNewDTO data, User author) {
        this.title = data.title();
        this.message = data.message();
        this.createdAt = LocalDateTime.now();
        this.author = author;
        this.status = TopicStatus.NOT_ANSWERED;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public TopicStatus getStatus() {
        return status;
    }
    public void setStatus(TopicStatus status) {
        this.status = status;
    }

    public List<Answer> addAnswer(Answer answer){
        this.answers.add(answer);
        return this.answers;
    }


    public List<Answer> getAnswers(){
        return this.answers;
    }
}
