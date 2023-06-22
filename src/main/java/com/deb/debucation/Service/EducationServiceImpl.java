package com.deb.debucation.Service;

import com.deb.debucation.entity.Address;
import com.deb.debucation.entity.Course;
import com.deb.debucation.entity.Student;
import com.deb.debucation.exceptions.BadRequestException;
import com.deb.debucation.exceptions.StudentNotFoundException;
import com.deb.debucation.repository.AddressRepository;
import com.deb.debucation.repository.CourseRepository;
import com.deb.debucation.repository.StudentRepositoy;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepositoy studentRepositoy;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional
    public Student addStudent(Student student) {

        Optional<Student> op= studentRepositoy.findByEmail(student.getEmail());
        if(op.isPresent())
        {
            throw new BadRequestException("Student already present");
        }
        return studentRepositoy.save(student);

    }

    @Override
    @Transactional
    public Student updateStudent(int id, Student student) {
        Optional<Student> op = studentRepositoy.findById(id);
        if(op.isEmpty())
        {
            throw new StudentNotFoundException("Student to update not present in database.");
        }
        student.setId(id);
        return studentRepositoy.save(student);
    }

    @Override
    @Transactional
    public Address addAddress(Address address) {
       return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(int studentId, Address address) {
        Optional<Student> op = studentRepositoy.findById(studentId);
        if(op.isEmpty())
        {
            throw new StudentNotFoundException("Student to update address not present in database.");
        }
        op.get().setAddress(address);
        studentRepositoy.save(op.get());
        return address;
    }

    @Override
    @Transactional
    public Course addCourse(Course course) {
        Optional<Course> op= courseRepository.findCourseByName(course.getName());
        if(!op.isPresent())
        {
            return courseRepository.save(course);
        }

        return null;

    }

    @Override
    public void deleteStudent(int id) {
        Optional<Student> op = studentRepositoy.findById(id);
        if(op.isEmpty())
        {
            throw new StudentNotFoundException("Student to delete not present in database.");
        }
        studentRepositoy.delete(op.get());
    }

    @Override
    @Transactional
    public List<Student> findAllStudents() {
        return studentRepositoy.findAll();
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<List<Course>> findCoursesForStudent(int studentId) {

        Optional<Student> stu = studentRepositoy.findById(studentId);
        List<Course> courses= stu.get().getCourses();

        return Optional.of(courses);
    }

    @Override
    public Optional<List<Student>> findStudentsEnrolledInCourse(String courseName) {

        Optional<Course> course = courseRepository.findCourseByName(courseName);
        List<Student> students= course.get().getStudents();

        return Optional.of(students);
    }

}
