package com.deb.debucation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Students")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50,nullable = false)
    private String name;
    @Column(length = 50, unique = true)
    private String email;
    @Column(length=10)
    private String phone;

    @OneToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Address_FK", referencedColumnName = "id")
    Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity = Course.class)
    @JoinColumn(name = "Course_FK",referencedColumnName = "id")
    List<Course> courses;

    public Student(String name, String email, String phone, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", courses=" + courses +
                '}';
    }
}
