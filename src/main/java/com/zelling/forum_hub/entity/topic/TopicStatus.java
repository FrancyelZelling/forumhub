package com.zelling.forum_hub.entity.topic;

public enum TopicStatus {
    ANSWERED("respondido"),
    NOT_ANSWERED("sem resposta");

    private String statusPT;

    private TopicStatus(String statusPT) {
        this.statusPT = statusPT;
    }

}
