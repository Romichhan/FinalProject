CREATE TABLE enrollments (
    id VARCHAR(36) PRIMARY KEY,

    student_id VARCHAR(36) NOT NULL,
    course_id VARCHAR(36) NOT NULL,

    status VARCHAR(50) NOT NULL,
    enrolled_at DATE,

    CONSTRAINT fk_enrollment_student
        FOREIGN KEY (student_id)
        REFERENCES student (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_enrollment_course
        FOREIGN KEY (course_id)
        REFERENCES courses (id),

    CONSTRAINT uc_studentcourse
        UNIQUE (student_id, course_id)
);
