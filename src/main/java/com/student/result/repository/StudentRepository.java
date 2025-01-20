package com.student.result.repository;

import com.student.result.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // important
    Optional<Student> findByRollNumber(String rollNumber);
    Optional<Student> findByRollNumberAndDateOfBirth(String rollNumber, LocalDate dateOfBirth);
}
