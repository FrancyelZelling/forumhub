CREATE TABLE courses(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    category VARCHAR(30)
);

ALTER TABLE topics
ADD COLUMN course_id BIGINT,
ADD CONSTRAINT fk_course_topic FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE SET NULL;