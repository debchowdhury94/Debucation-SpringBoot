package com.deb.debucation.controller;

import com.deb.debucation.Service.EducationService;
import com.deb.debucation.entity.Address;
import com.deb.debucation.entity.Course;
import com.deb.debucation.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/debucation")
public class EducationController {

    @Autowired
    private EducationService service;

    @RequestMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Student register(@RequestBody Student student)
    {
        Address address=student.getAddress();
        service.addAddress(address);
        List<Course> courses= student.getCourses();
        courses.stream().forEach(course -> service.addCourse(course));
        return service.addStudent(student);
    }

    @RequestMapping(value = "/addCourse",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Course addNewCourse(@RequestBody Course course)
    {
        List<Student> students = course.getStudents();
        students.stream().forEach(student -> service.addStudent(student));
        return service.addCourse(course);
    }

    @RequestMapping(value = "/findStudents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> findAllStudents()
    {
        return service.findAllStudents();
    }

    @RequestMapping(value = "/findCourses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> findAllCourses()
    {
        return service.findAllCourses();
    }

    @RequestMapping(value = "/findCourses/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> findAllCoursesForStudent(@PathVariable("id") int studentId)
    {
        return service.findCoursesForStudent(studentId).get();
    }

    @RequestMapping(value = "/findStudents/{course}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> findAllStudentsEnrolledInACourse(@PathVariable("course") String courseName)
    {
        return service.findStudentsEnrolledInCourse(courseName).get();
    }

    @RequestMapping(value = "/updateStudent/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudentDetails(@RequestBody Student student, @PathVariable("id") int studentId)
    {
        return service.updateStudent(studentId,student);
    }

    @RequestMapping(value = "/updateStudentAddress/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Address updateStudentAddress(@PathVariable("id") int studentId, @RequestBody Address address)
    {
        return service.updateAddress(studentId,address);
    }

    @RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") int studentId)
    {
        service.deleteStudent(studentId);
    }

    @RequestMapping(value = "/deleteCourse/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable("id") int courseId)
    {
        service.deleteStudent(courseId);
    }

}
