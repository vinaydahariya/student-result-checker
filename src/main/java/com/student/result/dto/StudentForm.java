package com.student.result.dto;

import com.student.result.entity.Mark;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Roll number is required")
    @Size(max = 20, message = "Roll number cannot exceed 20 characters")
    private String rollNumber;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(max = 1000, message = "Address cannot exceed 1000 characters")
    private String address;

    @NotBlank(message = "School name is required")
    @Size(max = 100, message = "School name cannot exceed 100 characters")
    private String schoolName;

    private String pictureName; // Optional, no validation needed unless required.

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Standard is required")
    @Pattern(regexp = "Class-(1[0-2]|[1-9])", message = "Standard must be 'Class-1' to 'Class-12'")
    private String standard;

    @NotBlank(message = "Father's name is required")
    @Size(max = 50, message = "Father's name cannot exceed 50 characters")
    private String fatherName;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be 'Male', 'Female', or 'Other'")
    private String gender;

    private List<MarkForm> marks = new ArrayList<>();
}
