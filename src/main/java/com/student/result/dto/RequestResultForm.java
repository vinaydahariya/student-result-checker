package com.student.result.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestResultForm {

    @NotBlank(message = "Roll number is required")
    @Size(max = 20, message = "Roll number cannot exceed 20 characters")
    private String rollNumber;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;  // Change from String to LocalDate
}
