package com.student.result.controller;

import com.student.result.dto.StudentForm;
import com.student.result.entity.Mark;
import com.student.result.entity.Student;
import com.student.result.repository.StudentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public AdminController(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add-result")
    public String addResultForm(Model model){
        StudentForm studentForm = new StudentForm();

        List<String> standardOptions = new ArrayList<>();
        for(int i=1; i<=12; i++){
            standardOptions.add("Class-"+i);
        }
        model.addAttribute("studentForm", studentForm);
        model.addAttribute("standardOptions", standardOptions);
        return "admin/add-result-form";
    }

    @RequestMapping(value = "/add-result-action", method = RequestMethod.POST)
    public String processAddResultForm(
            @Valid @ModelAttribute StudentForm studentForm,
            BindingResult bindingResult,
            Model model

    ){
        if (bindingResult.hasErrors()) {
            List<String> standardOptions = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                standardOptions.add("Class-"+i);
            }
            model.addAttribute("standardOptions", standardOptions);
            return "admin/add-result-form";
        }

            // convert student form to student entity
            Student student = modelMapper.map(studentForm, Student.class);

            // har marks ko attach student
            List<Mark> updatedList = student.getStudentMarks().stream().map(mark -> {
                mark.setStudent(student);
                return mark;
            }).toList();

            student.setId(UUID.randomUUID().toString());
            Student savedStudent = studentRepository.save(student);

            return "redirect:/admin/add-result?message=Student added successfully";
    }
}
