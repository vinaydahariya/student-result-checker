package com.student.result.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkForm {

    private String subjectName;
    private String marks;
    private String maxMarks;
    private String feedback;
    private String grade;

}
