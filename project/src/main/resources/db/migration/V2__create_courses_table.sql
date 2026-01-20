CREATE TABLE courses (
    id VARCHAR(36) PRIMARY KEY,
    course_code VARCHAR(255) UNIQUE,
    course_name VARCHAR(255)
);
