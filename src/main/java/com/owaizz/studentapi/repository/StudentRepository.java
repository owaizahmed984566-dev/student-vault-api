package com.owaizz.studentapi.repository;

import com.owaizz.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> id(int id);
}