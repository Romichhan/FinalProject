package com.sromey.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(
        {
                @NamedQuery(
                        name="Course.findCourseWithEnrollments",
                        query="SELECT c from Course c LEFT JOIN FETCH c.enrollments e JOIN FETCH e.student s"
                ),
                @NamedQuery(
                        name="Course.findCourseByCourseName",
                        query="SELECT c from Course c where courseName=:name"
                )
        }
)
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name="Course.findCourseNative",
                        query="SELECT * from courses c where c.course_name=:name",
                        resultClass = Course.class
                )
        }
)

public class Course {

    @Id
    private String id;

    @Column(unique = true)
    private String courseCode;

    private String courseName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();

    @PrePersist
    public void populateId(){
        if(id==null || id.isBlank()){
            id = UUID.randomUUID().toString();
        }
    }
}
