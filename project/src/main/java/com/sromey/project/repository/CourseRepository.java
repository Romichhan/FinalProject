package com.sromey.project.repository;

import com.sromey.project.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByCourseCodeIn(List<String> courseCodes);

    @Query(name="Course.findCourseWithEnrollments")
    List<Course> findCourseWithEnrollments();

    @Query(name="Course.findCourseByCourseName")
    Optional<Course> findCourseByCourseName(@Param("name") String courseName);

    @Query(name="Course.findCourseNative",nativeQuery = true)
    Optional<Course> findCourseNative(@Param("name") String courseName);
}
