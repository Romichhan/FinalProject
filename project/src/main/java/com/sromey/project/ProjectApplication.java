package com.sromey.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		//uncomment below code for testing
//		CourseRepository courseRepository = context.getBean(CourseRepository.class);
//		Course course = courseRepository.findCourseByCourseName("Logic Circuit Design").orElse(new Course());
//		System.out.println("Course is fetched:"+ course.getCourseCode()+", "+course.getCourseName());
//		Course course1 = courseRepository.findCourseNative("Logic Circuit Design").orElse(new Course());
//		System.out.println("Course is fetched using native query:"+course1.getCourseName()+"," +course1.getCourseCode());
//		//persistence context is shortly lived and lazily loaded entities will cause lazyinitialization
//		//BaseHelper entityHelper = (BaseHelper) context.getBean("entityHelper");
//		//can use the above for dynamic fetching of beans using bean name
//		EntityHelper entityHelper = context.getBean(EntityHelper.class);
//		entityHelper.execute();
//		entityHelper.test();
	}

//	@Bean
//	public EntityHelper entityHelper(){
//		return new EntityHelper();
//	}

}
