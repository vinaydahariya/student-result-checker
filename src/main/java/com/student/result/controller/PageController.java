package com.student.result.controller;

import com.student.result.dto.RequestResultForm;
import com.student.result.entity.Mark;
import com.student.result.entity.Student;
import com.student.result.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class PageController {

    private StudentRepository studentRepository;

    public PageController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view-result")
    public String viewResultForm(Model model){
        RequestResultForm requestResultForm = new RequestResultForm();
        model.addAttribute("requestResultForm", requestResultForm);
        return "view-result-form";
    }

    @PostMapping("/view-result-action")
    public String viewResult(
            @Valid @ModelAttribute RequestResultForm requestResultForm,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasFieldErrors()){
            return "view-result-form";
        }

        // result fetch and then view send
        Optional<Student> optionalStudent = studentRepository.findByRollNumberAndDateOfBirth(requestResultForm.getRollNumber(), requestResultForm.getDateOfBirth());
        if(optionalStudent.isEmpty()){
            return "redirect:/view-result?message=Student not found";
        }
        Student student = optionalStudent.get();
        List<Mark> marks = student.getStudentMarks();

        // calaculate total of the marks result:
        AtomicReference<Double> totalMarks = new AtomicReference<>(0.0);
        AtomicReference<Double> totalMaxMarks = new AtomicReference<>(0.0);
        marks.forEach(mark -> {
            totalMarks.set(totalMarks.get()+Double.parseDouble(mark.getMarks()));
            totalMaxMarks.set(totalMaxMarks.get()+Double.parseDouble(mark.getMaxMarks()));
        });

        double percentage = (totalMarks.get()/totalMaxMarks.get())*100;

        String passed = "fail";
        if(percentage>=33.33){
            passed = "pass";
        }

        model.addAttribute("student", student);
        model.addAttribute("marks", marks);
        model.addAttribute("percentage", percentage);
        model.addAttribute("totalMarks", totalMarks);
        model.addAttribute("totalMaxMarks", totalMaxMarks);
        model.addAttribute("passed", passed);
        model.addAttribute("currentDate", LocalDate.now().toString());
        return "view-result";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }
}
