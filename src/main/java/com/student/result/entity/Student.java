package com.student.result.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "show_student_result")
public class Student {
    @Id
    private String id;
    private String name;
    private String rollNumber;
    private String email;
    @Column(length = 1000)
    private String address;
    private String schoolName;
    private String pictureName;
    private LocalDate dateOfBirth;
    private String standard;
    private String fatherName;
    private String gender;
    // table field according to project

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mark> studentMarks = new ArrayList<>();

}
