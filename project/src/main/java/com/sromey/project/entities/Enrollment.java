package com.sromey.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="enrollments",
uniqueConstraints = {@UniqueConstraint(
        name="uc_studentcourse",
        columnNames = {"student_id","course_id"})})
public class Enrollment {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="student_id",nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="course_id",nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING) // so that we can send string in payload....jackson will automatically convert
    @Column(nullable = false)
    private EnrollmentStatus status;

    @Column
    private LocalDate enrolledAt; //expects in YYYY-MM-DD format

    public void addCourse(Course course){
        this.course = course;
        course.getEnrollments().add(this);
    };

    @PrePersist
    public void populateId(){
        if(id==null || id.isBlank()){
            id = UUID.randomUUID().toString();
        }
        if(enrolledAt==null){
            enrolledAt = LocalDate.now();
        }
    }


}
