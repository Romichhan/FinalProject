package com.sromey.project;

import com.sromey.project.entities.Course;
import com.sromey.project.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntityHelper extends BaseHelper {
    @Autowired
    private EntityManager em;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void execute(){
        //important this is used when you need it an object row format and you dont have the class to map it;
//        Object[] row = (Object[]) em.createNativeQuery("SELECT * from courses c where c.course_code=:code")
//                .setParameter("code","LCD1").getSingleResult();

         Course course = (Course) em.createNativeQuery("Select * from courses c where c.course_code=:code",Course.class)
                 .setParameter("code","LCD1").getSingleResult();
         System.out.println(course.getCourseCode());
         System.out.println(course.getEnrollments().size());
        List<Course> courseList = em.createNamedQuery("Course.findCourseWithEnrollments").getResultList();
        for(Course c: courseList){
            System.out.println(c.getCourseName());
            System.out.println(c.getEnrollments().size());
        }

    }

    @Transactional
    public void test(){
        Course course = courseRepository.findCourseByCourseName("Logic Circuit").orElse(new Course());
        System.out.println(course.getCourseName());
        course.setCourseName("Logic Circuit Design");
    }
}
