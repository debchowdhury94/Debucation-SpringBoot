package com.deb.debucation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Courses")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Course_name", length = 50, unique = true)
    private String name;

    @Column
    private String details;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Student.class)
   @JoinColumn(name = "Student_FK",referencedColumnName = "id")
    List<Student> students;

    public Course(String name, String details) {
        this.name = name;
        this.details = details;
    }
}
