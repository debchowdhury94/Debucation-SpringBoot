package com.deb.debucation.repository;

import com.deb.debucation.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findCourseByName(String name);
}
