package com.sromey.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(
        {
                @NamedQuery(name="Student.findStudentWithEnrollment",
                query="SELECT DISTINCT s from Student s LEFT JOIN FETCH s.enrollments e JOIN FETCH e.course c")
        }
)
public class Student {

    @Id
    @Column(name="id")
    private String id;

    private String name;

    @Column(unique = true)
    private String rollNo;

    @Embedded
    private Address address;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Enrollment> enrollments = new ArrayList<>();

    public void addEnrollment(Enrollment enrollment){
        enrollments.add(enrollment);
        enrollment.setStudent(this);
    }

    public void removeEnrollment(Enrollment enrollment){
        enrollments.remove(enrollment);
        enrollment.setStudent(null);
    }

    @PrePersist
    public void populateId(){
        if(id==null || id.isBlank()){
            id = UUID.randomUUID().toString();
        }
    }


}
