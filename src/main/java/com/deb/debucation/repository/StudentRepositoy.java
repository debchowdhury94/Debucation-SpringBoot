package com.deb.debucation.repository;

import com.deb.debucation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepositoy extends JpaRepository<Student,Integer> {
    Optional<Student> findByEmail(String email);
}
