CREATE TABLE answers(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `message` TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    author_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,

    CONSTRAINT fk_answer_author FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_answer_topic FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE CASCADE
)
