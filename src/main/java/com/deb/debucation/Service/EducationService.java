package com.deb.debucation.Service;

import com.deb.debucation.entity.Address;
import com.deb.debucation.entity.Course;
import com.deb.debucation.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EducationService {

    Student addStudent(Student student);
    Student updateStudent(int id,Student student);
    Course addCourse(Course course);
    void deleteStudent(int id);
    Address addAddress(Address address);
    Address updateAddress(int studentId, Address address);
    List<Student> findAllStudents();
    List<Course> findAllCourses();
    Optional<List<Course>> findCoursesForStudent(int studentId);
    Optional<List<Student>> findStudentsEnrolledInCourse(String courseName);


}
